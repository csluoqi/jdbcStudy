package baseDao.domain;


import java.util.Date;

public class Bok
{
    private Long id;
    private String name;
    private String author;
    private Double price;
    private String info;
    private Date publishDate;
    private Integer amount;
    
    public Integer getAmount()
    {
        return amount;
    }
    public void setAmount(Integer amount)
    {
        this.amount = amount;
    }
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
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
    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public Double getPrice()
    {
        return price;
    }
    public void setPrice(Double price)
    {
        this.price = price;
    }
    public String getInfo()
    {
        return info;
    }
    public void setInfo(String info)
    {
        this.info = info;
    }
    public Date getPublishDate()
    {
        return publishDate;
    }
    public void setPublishDate(Date publishDate)
    {
        this.publishDate = publishDate;
    }
    @Override
    public String toString()
    {
        return "Bok [id=" + id + ", name=" + name + ", author=" + author
                + ", price=" + price + ", info=" + info + ", publishDate="
                + publishDate + "]";
    }
    
    
}
