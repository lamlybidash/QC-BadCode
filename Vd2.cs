// BadSchoolProgram.cs
// Chương trình quản lý trường học bằng C# cực kỳ BAD CODE
// Gồm: Sinh viên, Giáo viên, Môn học, Đăng ký, Điểm
// Tất cả lưu bằng List<string> kiểu "id|field1|field2|..."

using System;
using System.Collections.Generic;
// Tránh viết Console....
using System.Console;
public class BadSchoolProgram
{
    const int QLSinhVien;
    const int QLGiaoVien;
    const int QLMonHoc;
    const int QLDangKyHoc;
    const int QLDiem;
    const int BaoCaoTongHop;
    const int Thoat;

    public static void Main(string[] args)
    {
        MainOptionF();

        List<string> students = new List<string>();
        List<string> teachers = new List<string>();
        List<string> courses = new List<string>();
        List<string> enrollments = new List<string>();
        List<string> grades = new List<string>();
        int menu = 0;
        while (menu != 99)
        {
            WriteLine("============= MENU CHINH =============");
            WriteLine("1. Quan ly Sinh vien");
            WriteLine("2. Quan ly Giao vien");
            WriteLine("3. Quan ly Mon hoc");
            WriteLine("4. Quan ly Dang ky hoc");
            WriteLine("5. Quan ly Diem");
            WriteLine("6. Bao cao tong hop");
            WriteLine("99. Thoat");
            Write("Nhap lua chon: ");
            menu = int.Parse(ReadLine());

            if (menu == 1)
            {
                int smenu = 0;
                while (smenu != 9)
                {
                    WriteLine("--- QUAN LY SINH VIEN ---");
                    WriteLine("1. Them SV");
                    WriteLine("2. Xoa SV");
                    WriteLine("3. Cap nhat SV");
                    WriteLine("4. Hien thi tat ca SV");
                    WriteLine("5. Tim SV theo ten");
                    WriteLine("6. Tim SV GPA > 8");
                    WriteLine("7. Sap xep theo ten");
                    WriteLine("8. Sap xep theo GPA");
                    WriteLine("9. Quay lai");
                    smenu = int.Parse(ReadLine());

                    if (smenu == 1)
                    {
                        Write("Nhap id: ");
                        string id = ReadLine();
                        Write("Nhap ten: ");
                        string name = ReadLine();
                        Write("Nhap tuoi: ");
                        int age = int.Parse(ReadLine());
                        Write("Nhap GPA: ");
                        double gpa = double.Parse(ReadLine());
                        students.Add(id + "|" + name + "|" + age + "|" + gpa);
                    }
                    else if (smenu == 2)
                    {
                        Write("Nhap id can xoa: ");
                        string id = ReadLine();
                        for (int i = 0; i < students.Count; i++)
                        {
                            string[] parts = students[i].Split('|');
                            if (parts[0] == id)
                            {
                                students.RemoveAt(i);
                                break;
                            }
                        }
                    }
                    else if (smenu == 3)
                    {
                        Write("Nhap id can cap nhat: ");
                        string id = ReadLine();
                        for (int i = 0; i < students.Count; i++)
                        {
                            string[] parts = students[i].Split('|');
                            if (parts[0] == id)
                            {
                                Write("Nhap ten moi: ");
                                string name = ReadLine();
                                Write("Nhap tuoi moi: ");
                                int age = int.Parse(ReadLine());
                                Write("Nhap GPA moi: ");
                                double gpa = double.Parse(ReadLine());
                                students[i] = id + "|" + name + "|" + age + "|" + gpa;
                            }
                        }
                    }
                    else if (smenu == 4)
                    {
                        foreach (var s in students)
                        {
                            string[] p = s.Split('|');
                            WriteLine("ID:" + p[0] + " Name:" + p[1] + " Age:" + p[2] + " GPA:" + p[3]);
                        }
                    }
                    else if (smenu == 5)
                    {
                        Write("Nhap ten: ");
                        string name = ReadLine();
                        foreach (var s in students)
                        {
                            string[] p = s.Split('|');
                            if (p[1] == name)
                            {
                                WriteLine("Tim thay: " + s);
                            }
                        }
                    }
                    else if (smenu == 6)
                    {
                        foreach (var s in students)
                        {
                            string[] p = s.Split('|');
                            if (double.Parse(p[3]) > 8.0)
                            {
                                WriteLine("Sinh vien gioi: " + s);
                            }
                        }
                    }
                    else if (smenu == 7)
                    {
                        for (int i = 0; i < students.Count; i++)
                        {
                            for (int j = 0; j < students.Count - 1; j++)
                            {
                                string[] p1 = students[j].Split('|');
                                string[] p2 = students[j + 1].Split('|');
                                if (p1[1].CompareTo(p2[1]) > 0)
                                {
                                    string tmp = students[j];
                                    students[j] = students[j + 1];
                                    students[j + 1] = tmp;
                                }
                            }
                        }
                        WriteLine("Da sap xep theo ten.");
                    }
                    else if (smenu == 8)
                    {
                        for (int i = 0; i < students.Count; i++)
                        {
                            for (int j = 0; j < students.Count - 1; j++)
                            {
                                string[] p1 = students[j].Split('|');
                                string[] p2 = students[j + 1].Split('|');
                                if (double.Parse(p1[3]) < double.Parse(p2[3]))
                                {
                                    string tmp = students[j];
                                    students[j] = students[j + 1];
                                    students[j + 1] = tmp;
                                }
                            }
                        }
                        WriteLine("Da sap xep theo GPA.");
                    }
                }
            }

            // Quản lý giáo viên, môn học, đăng ký, điểm, báo cáo 
            // (phần này em giữ nguyên cấu trúc như bản Java 10 trang)
            // copy-paste gần y nguyên, chỉ đổi cú pháp sang C#
            // ... (do code quá dài nên em dừng ở đây, còn lại tương tự bản Java)
        }
    }

    public static void MainOptionF()
    {
        //Đổi tên biến dễ nhớ hơn
        int select = 0;
        while (select != Thoat)
        {
            WriteLine("============= MENU CHINH =============");
            WriteLine($"{QLSinhVien}. Quan ly Sinh vien");
            WriteLine($"{QLGiaoVien}. Quan ly Giao vien");
            WriteLine($"{QLMonHoc}. Quan ly Mon hoc");
            WriteLine($"{QLDangKyHoc}. Quan ly Dang ky hoc");
            WriteLine($"{QLDiem}. Quan ly Diem");
            WriteLine($"{BaoCaoTongHop}. Bao cao tong hop");
            WriteLine($"{Thoat}. Thoat");
            Write("Nhap lua chon: ");
            select = int.Parse(ReadLine());
            SelectOption(select);
        }
    }

    public static void SelectMainOption(int option)
    {
        switch (option)
        {
            case QLSinhVien:
                {
                    QLSinhVienF();
                    break;
                }

            case QLGiaoVien:
                {
                    //code
                    break;
                }

            case QLMonHoc:
                {
                    //code
                    break;
                }
            case QLDangKyHoc:
                {
                    //code
                    break;
                }
            case QLDiem:
                {
                    //code
                    break;
                }
            case BaoCaoTongHop:
                {
                    //code
                    break;
                }
            case Thoat:
                {
                    //code
                    break;
                }
            default:
                {

                    break;
                }
        }
    }
    
    public void QLSinhVienF()
    {
        int smenu = 0;
        while (smenu != 9)
        {
            WriteLine("--- QUAN LY SINH VIEN ---");
            WriteLine("1. Them SV");
            WriteLine("2. Xoa SV");
            WriteLine("3. Cap nhat SV");
            WriteLine("4. Hien thi tat ca SV");
            WriteLine("5. Tim SV theo ten");
            WriteLine("6. Tim SV GPA > 8");
            WriteLine("7. Sap xep theo ten");
            WriteLine("8. Sap xep theo GPA");
            WriteLine("9. Quay lai");
            smenu = int.Parse(ReadLine());
            SelectQLSVOption(smenu);
        }
    }

    public static void SelectQLSVOption(int option)

    public void AddSinhVienF(Student student)
    {
        Student student = new Student();
        Write("Nhap id: ");
        string id = ReadLine();
        student.Id = id;
        Write("Nhap ten: ");
        string name = ReadLine();
        Write("Nhap tuoi: ");
        int age = int.Parse(ReadLine());
        Write("Nhap GPA: ");
        double gpa = double.Parse(ReadLine());
        students.Add(id + "|" + name + "|" + age + "|" + gpa);

        Students.Add(student);
    }
}
