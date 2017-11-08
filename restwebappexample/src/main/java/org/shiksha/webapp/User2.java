package org.shiksha.webapp;

public class User2
{
    private int id;
    private String name;
    private String profession;

    public User2(int id, String name, String profession)
    {
        this.id = id;
        this.name = name;
        this.profession = profession;
    }

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

    public String getProfession()
    {
        return profession;
    }

    public void setProfession(String profession)
    {
        this.profession = profession;
    }

    @Override
    public boolean equals(Object object)
    {
        if(null == object)
            return false;
        else if(!(object instanceof User2))
            return false;
        else
        {
            User2 user = (User2)object;
            if(id == user.getId() && name == user.getName() && profession == user.getProfession())
                return true;
        }
        return false;
    }
}
