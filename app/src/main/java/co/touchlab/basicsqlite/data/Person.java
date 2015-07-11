package co.touchlab.basicsqlite.data;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by kgalligan on 7/11/15.
 */
@DatabaseTable
public class Person
{
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField
    private int age;

    @DatabaseField
    private String favoriteColor;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getFavoriteColor()
    {
        return favoriteColor;
    }

    public void setFavoriteColor(String favoriteColor)
    {
        this.favoriteColor = favoriteColor;
    }

    @Override
    public String toString()
    {
        return id + " " + name + "("+ age +") "+ favoriteColor;
    }
}
