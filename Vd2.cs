// BadSchoolProgram.cs
// Chương trình quản lý trường học bằng C# cực kỳ BAD CODE
// Gồm: Sinh viên, Giáo viên, Môn học, Đăng ký, Điểm
// Tất cả lưu bằng List<string> kiểu "id|field1|field2|..."

using System;
using System.Collections.Generic;
// Tránh viết Console....
using System.Console;

//test
public class BadSchoolProgram
{
    List<Student> students = new List<Student>();
    List<Teachers> teachers = new List<string>();
    List<string> courses = new List<string>();
    List<string> enrollments = new List<string>();
    List<string> grades = new List<string>();

    public static void Main(string[] args)
    {
        MainOptionF();
    }

    const int QLSinhVien = 1;
    const int QLGiaoVien = 2;
    const int QLMonHoc = 3;
    const int QLDangKyHoc = 4;
    const int QLDiem = 5;
    const int BaoCaoTongHop = 6;
    const int Thoat = 99;
    public static void MainOptionF()
    {
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
                    QLGiaoVienF();
                    break;
                }

            case QLMonHoc:
                {
                    QLMonHoc();
                    break;
                }
            case QLDangKyHoc:
                {
                    QLDangKyHoc();
                    break;
                }
            case QLDiem:
                {
                    QLDiem();
                    break;
                }
            case BaoCaoTongHop:
                {
                    BaoCaoTongHop();
                    break;
                }
            case Thoat:
                {
                    break;
                }
        }
    }

    public const int StudentAdd = 1;
    public const int StudentDel = 2;
    public const int StudentUpdate = 3;
    public const int StudentAll = 4;
    public const int StudentFilterName = 5;
    public const int StudentFilterGPA = 6;
    public const int StudentSortName = 7;
    public const int StudentSortGPA = 8;
    public const int StudentBreak = 0;
    public void QLSinhVienF()
    {
        int StudentMenu = 0;
        while (StudentMenu != 9)
        {
            WriteLine("--- QUAN LY SINH VIEN ---");
            WriteLine($"{StudentAdd}. Them SV");
            WriteLine($"{StudentDel}. Xoa SV");
            WriteLine($"{StudentUpdate}. Cap nhat SV");
            WriteLine($"{StudentAll}. Hien thi tat ca SV");
            WriteLine($"{StudentFilterName}. Tim SV theo ten");
            WriteLine($"{StudentFilterGPA}. Tim SV GPA > 8");
            WriteLine($"{StudentSortName}. Sap xep theo ten");
            WriteLine($"{StudentSortGPA}. Sap xep theo GPA");
            WriteLine($"{StudentBreak}. Quay lai");
            StudentMenu = int.Parse(ReadLine());
            SelectQLSVOption(StudentMenu);
        }
    }
    public static void SelectQLSVOption(int option)
    {

        switch (option)
        {
            case StudentAdd:
                {
                    AddSinhVienF();
                    break;
                }
            case StudentDel:
                {
                    DelSinhVienF();
                    break;
                }
            case StudentUpdate:
                {
                    UpdateSinhVienF();
                    break;
                }
            case StudentAll:
                {
                    AllSinhVienF();
                    break;
                }
            case StudentFilterGPA:
                {
                    FilterGPASinhVienF();
                    break;
                }
            case StudentFilterName:
                {
                    FilterNameSinhVienF();
                    break;
                }
            case StudentSortGPA:
                {
                    SortGPASinhVienF();
                    break;
                }
            case StudentSortName:
                {
                    SortNameSinhVienF();
                    break;
                }
            case StudentBreak:
                {
                    break;
                }


        }
    }
    public void AddSinhVienF(Student student)
    {
        Student student = new Student();

        Write("Nhap id: ");
        student.Id = ReadLine();

        Write("Nhap ten: ");
        student.name = ReadLine();

        Write("Nhap tuoi: ");
        student.age = int.Parse(ReadLine());

        Write("Nhap GPA: ");
        student.gpa = double.Parse(ReadLine());

        Students.Add(student);
    }
    public void DelSinhVienF()
    {
        //Logic
    }
    public void UpdateSinhVienF()
    {
        //Logic
    }
    public void AllSinhVienF()
    {
        //Logic
    }
    public void FilterGPASinhVienF()
    {
        //Logic
    }
    public void FilterNameSinhVienF()
    {
        //Logic
    }
}