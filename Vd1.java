import java.util.*;
import java.util.stream.Collectors;

public class BadSchoolProgram {

    // ======= Models =======
    static class Student {
        String id;
        String name;
        int age;
        double gpa;

        Student(String id, String name, int age, double gpa) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.gpa = gpa;
        }
    }

    static class Teacher {
        String id;
        String name;
        String major;

        Teacher(String id, String name, String major) {
            this.id = id;
            this.name = name;
            this.major = major;
        }
    }

    static class Course {
        String id;
        String name;
        int credits;

        Course(String id, String name, int credits) {
            this.id = id;
            this.name = name;
            this.credits = credits;
        }
    }

    static class Enrollment {
        String studentId;
        String courseId;

        Enrollment(String studentId, String courseId) {
            this.studentId = studentId;
            this.courseId = courseId;
        }
    }

    static class Grade {
        String studentId;
        String courseId;
        double score;

        Grade(String studentId, String courseId, double score) {
            this.studentId = studentId;
            this.courseId = courseId;
            this.score = score;
        }
    }

    // ======= Repositories (in-memory) =======
    static class Repo {
        Map<String, Student> students = new LinkedHashMap<>();
        Map<String, Teacher> teachers = new LinkedHashMap<>();
        Map<String, Course> courses = new LinkedHashMap<>();
        // enrollments & grades có thể để List vì là quan hệ nhiều–nhiều
        List<Enrollment> enrollments = new ArrayList<>();
        List<Grade> grades = new ArrayList<>();
    }

    // ======= Menu =======
    enum MainMenu {
        STUDENTS(1), TEACHERS(2), COURSES(3), ENROLL(4), GRADES(5), REPORT(6), EXIT(99);
        final int code;
        MainMenu(int c) { this.code = c; }
        static Optional<MainMenu> from(int c) {
            return Arrays.stream(values()).filter(m -> m.code == c).findFirst();
        }
    }

    // ======= IO Helpers =======
    static class IO {
        private final Scanner sc = new Scanner(System.in);

        String readLine(String prompt) {
            System.out.print(prompt);
            return sc.nextLine().trim();
        }

        int readInt(String prompt) {
            while (true) {
                System.out.print(prompt);
                String s = sc.nextLine().trim();
                try { return Integer.parseInt(s); }
                catch (NumberFormatException e) { System.out.println(">> Nhập số nguyên hợp lệ!"); }
            }
        }

        double readDouble(String prompt) {
            while (true) {
                System.out.print(prompt);
                String s = sc.nextLine().trim();
                try { return Double.parseDouble(s); }
                catch (NumberFormatException e) { System.out.println(">> Nhập số thực hợp lệ!"); }
            }
        }
    }

    // ======= App =======
    public static void main(String[] args) {
        Repo repo = new Repo();
        IO io = new IO();

        // seed nhẹ cho demo
        repo.students.put("S1", new Student("S1","An",20,8.6));
        repo.students.put("S2", new Student("S2","Bình",19,7.8));
        repo.courses.put("C1", new Course("C1","CTDL",3));
        repo.courses.put("C2", new Course("C2","LT HĐT",4));

        while (true) {
            System.out.println("\n============= MENU CHÍNH =============");
            System.out.println("1. Quản lý Sinh viên");
            System.out.println("2. Quản lý Giáo viên");
            System.out.println("3. Quản lý Môn học");
            System.out.println("4. Quản lý Đăng ký học");
            System.out.println("5. Quản lý Điểm");
            System.out.println("6. Báo cáo tổng hợp");
            System.out.println("99. Thoát");
            int choice = new IO().readInt("Nhập lựa chọn: ");

            Optional<MainMenu> menu = MainMenu.from(choice);
            if (menu.isEmpty()) {
                System.out.println(">> Lựa chọn không hợp lệ!");
                continue;
            }
            if (menu.get() == MainMenu.EXIT) {
                System.out.println("Tạm biệt!");
                break;
            }

            switch (menu.get()) {
                case STUDENTS -> studentMenu(repo, io);
                case TEACHERS -> teacherMenu(repo, io);
                case COURSES -> courseMenu(repo, io);
                case ENROLL -> enrollmentMenu(repo, io);
                case GRADES -> gradeMenu(repo, io);
                case REPORT -> report(repo);
                default -> System.out.println(">> Không hỗ trợ!");
            }
        }
    }

    // ======= Student Menu =======
    static void studentMenu(Repo repo, IO io) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ SINH VIÊN ---");
            System.out.println("1. Thêm SV");
            System.out.println("2. Xóa SV");
            System.out.println("3. Cập nhật SV");
            System.out.println("4. Hiển thị tất cả");
            System.out.println("5. Tìm theo tên");
            System.out.println("6. Lọc GPA > 8");
            System.out.println("7. Sắp xếp theo tên");
            System.out.println("8. Sắp xếp theo GPA (giảm dần)");
            System.out.println("9. Quay lại");
            int c = new IO().readInt("Chọn: ");
            if (c == 9) return;

            switch (c) {
                case 1 -> {
                    String id = io.readLine("ID: ");
                    if (repo.students.containsKey(id)) { System.out.println(">> ID đã tồn tại!"); break; }
                    String name = io.readLine("Tên: ");
                    int age = io.readInt("Tuổi: ");
                    double gpa = io.readDouble("GPA: ");
                    repo.students.put(id, new Student(id, name, age, gpa));
                    System.out.println(">> Đã thêm.");
                }
                case 2 -> {
                    String id = io.readLine("ID cần xóa: ");
                    if (repo.students.remove(id) != null) System.out.println(">> Đã xóa.");
                    else System.out.println(">> Không tìm thấy SV.");
                }
                case 3 -> {
                    String id = io.readLine("ID cần cập nhật: ");
                    Student s = repo.students.get(id);
                    if (s == null) { System.out.println(">> Không tìm thấy."); break; }
                    String name = io.readLine("Tên mới ("+s.name+"): ");
                    int age = io.readInt("Tuổi mới ("+s.age+"): ");
                    double gpa = io.readDouble("GPA mới ("+s.gpa+"): ");
                    repo.students.put(id, new Student(id, name.isEmpty()?s.name:name, age, gpa));
                    System.out.println(">> Đã cập nhật.");
                }
                case 4 -> {
                    if (repo.students.isEmpty()) { System.out.println("(trống)"); break; }
                    repo.students.values().forEach(s ->
                        System.out.println("ID:"+s.id+" | Name:"+s.name+" | Age:"+s.age+" | GPA:"+s.gpa));
                }
                case 5 -> {
                    String kw = io.readLine("Nhập tên (tìm gần đúng): ").toLowerCase();
                    repo.students.values().stream()
                        .filter(s -> s.name.toLowerCase().contains(kw))
                        .forEach(s -> System.out.println("Tìm thấy: "+s.id+" | "+s.name+" | GPA:"+s.gpa));
                }
                case 6 -> repo.students.values().stream()
                        .filter(s -> s.gpa > 8.0)
                        .forEach(s -> System.out.println("SV giỏi: "+s.id+" | "+s.name+" | "+s.gpa));
                case 7 -> {
                    List<Student> sorted = new ArrayList<>(repo.students.values());
                    sorted.sort(Comparator.comparing(s -> s.name.toLowerCase()));
                    sorted.forEach(s -> System.out.println(s.id+" | "+s.name+" | "+s.gpa));
                }
                case 8 -> {
                    List<Student> sorted = new ArrayList<>(repo.students.values());
                    sorted.sort(Comparator.comparingDouble((Student s) -> s.gpa).reversed());
                    sorted.forEach(s -> System.out.println(s.id+" | "+s.name+" | "+s.gpa));
                }
                default -> System.out.println(">> Không hợp lệ!");
            }
        }
    }

    // ======= Teacher Menu =======
    static void teacherMenu(Repo repo, IO io) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ GIÁO VIÊN ---");
            System.out.println("1. Thêm GV");
            System.out.println("2. Xóa GV");
            System.out.println("3. Cập nhật GV");
            System.out.println("4. Hiển thị GV");
            System.out.println("9. Quay lại");
            int c = new IO().readInt("Chọn: ");
            if (c == 9) return;

            switch (c) {
                case 1 -> {
                    String id = io.readLine("ID: ");
                    if (repo.teachers.containsKey(id)) { System.out.println(">> ID đã tồn tại!"); break; }
                    String name = io.readLine("Tên: ");
                    String major = io.readLine("Chuyên môn: ");
                    repo.teachers.put(id, new Teacher(id, name, major));
                    System.out.println(">> Đã thêm.");
                }
                case 2 -> {
                    String id = io.readLine("ID cần xóa: ");
                    System.out.println(repo.teachers.remove(id) != null ? ">> Đã xóa." : ">> Không tìm thấy.");
                }
                case 3 -> {
                    String id = io.readLine("ID cần cập nhật: ");
                    Teacher t = repo.teachers.get(id);
                    if (t == null) { System.out.println(">> Không tìm thấy."); break; }
                    String name = io.readLine("Tên mới ("+t.name+"): ");
                    String major = io.readLine("Chuyên môn mới ("+t.major+"): ");
                    repo.teachers.put(id, new Teacher(id, name.isEmpty()?t.name:name, major.isEmpty()?t.major:major));
                    System.out.println(">> Đã cập nhật.");
                }
                case 4 -> repo.teachers.values().forEach(t ->
                    System.out.println("ID:"+t.id+" | Name:"+t.name+" | Major:"+t.major));
                default -> System.out.println(">> Không hợp lệ!");
            }
        }
    }

    // ======= Course Menu =======
    static void courseMenu(Repo repo, IO io) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ MÔN HỌC ---");
            System.out.println("1. Thêm MH");
            System.out.println("2. Xóa MH");
            System.out.println("3. Cập nhật MH");
            System.out.println("4. Hiển thị MH");
            System.out.println("9. Quay lại");
            int c = new IO().readInt("Chọn: ");
            if (c == 9) return;

            switch (c) {
                case 1 -> {
                    String id = io.readLine("ID: ");
                    if (repo.courses.containsKey(id)) { System.out.println(">> ID đã tồn tại!"); break; }
                    String name = io.readLine("Tên MH: ");
                    int credits = io.readInt("Tín chỉ: ");
                    repo.courses.put(id, new Course(id, name, credits));
                    System.out.println(">> Đã thêm.");
                }
                case 2 -> {
                    String id = io.readLine("ID cần xóa: ");
                    System.out.println(repo.courses.remove(id) != null ? ">> Đã xóa." : ">> Không tìm thấy.");
                }
                case 3 -> {
                    String id = io.readLine("ID cần cập nhật: ");
                    Course cobj = repo.courses.get(id);
                    if (cobj == null) { System.out.println(">> Không tìm thấy."); break; }
                    String name = io.readLine("Tên mới ("+cobj.name+"): ");
                    int credits = io.readInt("Tín chỉ mới ("+cobj.credits+"): ");
                    repo.courses.put(id, new Course(id, name.isEmpty()?cobj.name:name, credits));
                    System.out.println(">> Đã cập nhật.");
                }
                case 4 -> repo.courses.values().forEach(cobj ->
                    System.out.println("ID:"+cobj.id+" | Name:"+cobj.name+" | TinChi:"+cobj.credits));
                default -> System.out.println(">> Không hợp lệ!");
            }
        }
    }

    // ======= Enrollment Menu =======
    static void enrollmentMenu(Repo repo, IO io) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ ĐĂNG KÝ HỌC ---");
            System.out.println("1. Đăng ký môn");
            System.out.println("2. Hủy đăng ký");
            System.out.println("3. Xem tất cả");
            System.out.println("9. Quay lại");
            int c = new IO().readInt("Chọn: ");
            if (c == 9) return;

            switch (c) {
                case 1 -> {
                    String sid = io.readLine("ID SV: ");
                    String cid = io.readLine("ID MH: ");
                    if (!repo.students.containsKey(sid) || !repo.courses.containsKey(cid)) {
                        System.out.println(">> SV hoặc MH không tồn tại.");
                        break;
                    }
                    boolean exists = repo.enrollments.stream()
                            .anyMatch(e -> e.studentId.equals(sid) && e.courseId.equals(cid));
                    if (exists) { System.out.println(">> Đã đăng ký trước đó."); break; }
                    repo.enrollments.add(new Enrollment(sid, cid));
                    System.out.println(">> Đã đăng ký.");
                }
                case 2 -> {
                    String sid = io.readLine("ID SV: ");
                    String cid = io.readLine("ID MH: ");
                    boolean removed = repo.enrollments.removeIf(e -> e.studentId.equals(sid) && e.courseId.equals(cid));
                    System.out.println(removed ? ">> Đã hủy." : ">> Không tìm thấy đăng ký.");
                }
                case 3 -> {
                    if (repo.enrollments.isEmpty()) { System.out.println("(trống)"); break; }
                    for (Enrollment e : repo.enrollments) {
                        String sName = Optional.ofNullable(repo.students.get(e.studentId)).map(s -> s.name).orElse("?");
                        String cName = Optional.ofNullable(repo.courses.get(e.courseId)).map(cobj -> cobj.name).orElse("?");
                        System.out.println("SV: "+e.studentId+"("+sName+") -> MH: "+e.courseId+"("+cName+")");
                    }
                }
                default -> System.out.println(">> Không hợp lệ!");
            }
        }
    }

    // ======= Grade Menu =======
    static void gradeMenu(Repo repo, IO io) {
        while (true) {
            System.out.println("\n--- QUẢN LÝ ĐIỂM ---");
            System.out.println("1. Nhập điểm");
            System.out.println("2. Cập nhật điểm");
            System.out.println("3. Hiển thị điểm");
            System.out.println("9. Quay lại");
            int c = new IO().readInt("Chọn: ");
            if (c == 9) return;

            switch (c) {
                case 1 -> {
                    String sid = io.readLine("ID SV: ");
                    String cid = io.readLine("ID MH: ");
                    if (!repo.students.containsKey(sid) || !repo.courses.containsKey(cid)) {
                        System.out.println(">> SV hoặc MH không tồn tại.");
                        break;
                    }
                    double score = io.readDouble("Điểm: ");
                    // nếu đã có thì cập nhật, chưa có thì thêm
                    Optional<Grade> g = repo.grades.stream()
                            .filter(x -> x.studentId.equals(sid) && x.courseId.equals(cid))
                            .findFirst();
                    if (g.isPresent()) {
                        g.get().score = score;
                        System.out.println(">> Đã cập nhật điểm.");
                    } else {
                        repo.grades.add(new Grade(sid, cid, score));
                        System.out.println(">> Đã nhập điểm.");
                    }
                }
                case 2 -> {
                    String sid = io.readLine("ID SV: ");
                    String cid = io.readLine("ID MH: ");
                    Optional<Grade> g = repo.grades.stream()
                            .filter(x -> x.studentId.equals(sid) && x.courseId.equals(cid))
                            .findFirst();
                    if (g.isEmpty()) { System.out.println(">> Chưa có điểm để cập nhật."); break; }
                    double score = io.readDouble("Điểm mới: ");
                    g.get().score = score;
                    System.out.println(">> Đã cập nhật.");
                }
                case 3 -> {
                    if (repo.grades.isEmpty()) { System.out.println("(trống)"); break; }
                    for (Grade gr : repo.grades) {
                        String sName = Optional.ofNullable(repo.students.get(gr.studentId)).map(s -> s.name).orElse("?");
                        String cName = Optional.ofNullable(repo.courses.get(gr.courseId)).map(cobj -> cobj.name).orElse("?");
                        System.out.println("SV:"+gr.studentId+"("+sName+") | MH:"+gr.courseId+"("+cName+") | Điểm:"+gr.score);
                    }
                }
                default -> System.out.println(">> Không hợp lệ!");
            }
        }
    }

    // ======= Report =======
    static void report(Repo repo) {
        System.out.println("\n=== BÁO CÁO TỔNG HỢP ===");
        if (repo.students.isEmpty()) { System.out.println("(không có SV)"); return; }

        // Gom điểm theo (sid -> (cid -> score))
        Map<String, Map<String, Double>> scoresByStudent = new HashMap<>();
        for (Grade g : repo.grades) {
            scoresByStudent
                .computeIfAbsent(g.studentId, k -> new HashMap<>())
                .put(g.courseId, g.score);
        }

        // Gom đăng ký theo SV
        Map<String, List<String>> coursesByStudent = repo.enrollments.stream()
                .collect(Collectors.groupingBy(e -> e.studentId,
                        Collectors.mapping(e -> e.courseId, Collectors.toList())));

        for (Student s : repo.students.values()) {
            System.out.println("Sinh viên: " + s.name + " (GPA: " + s.gpa + ")");
            List<String> enrolled = coursesByStudent.getOrDefault(s.id, Collections.emptyList());
            if (enrolled.isEmpty()) {
                System.out.println("  (chưa đăng ký môn)");
                continue;
            }
            for (String cid : enrolled) {
                Course c = repo.courses.get(cid);
                String cname = (c == null) ? cid : c.name;
                Double sc = Optional.ofNullable(scoresByStudent.get(s.id))
                        .map(m -> m.get(cid)).orElse(null);
                System.out.println("  - Môn: " + cname + (sc != null ? " | Điểm: " + sc : " | Điểm: (chưa có)"));
            }
        }
    }
}
