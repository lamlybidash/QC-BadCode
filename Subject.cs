public class Subjects{
    public string Id { get; set; }
    public string Name { get; set; }
    public int Credits { get; set; }

    public Subjects(string id, string name, int credits)
    {
        Id = id;
        Name = name;
        Credits = credits;
    }
}