public class Teachers{
    public string Id { get; set; }
    public string Name { get; set; }
    public int Age { get; set; }
    public string Major { get; set; }

    public Teachers(string id, string name, int age, string major)
    {
        Id = id;
        Name = name;
        Age = age;
        Major = major;
    }
}