package co.touchlab.basicsqlite;
/**
 * Created by kgalligan on 7/11/15.
 */
public class Person
{
    public final int id;
    public final String name;
    public final int age;
    public final String favoriteColor;

    public Person(int id, String name, int age, String favoriteColor)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.favoriteColor = favoriteColor;
    }

    @Override
    public String toString()
    {
        return id + " " + name + "("+ age +") "+ favoriteColor;
    }
}
