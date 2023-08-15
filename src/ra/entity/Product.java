package ra.entity;

import ra.IShop;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements IShop<Product> {
    //1 Attribute.
    private String producId;
    private String productName;
    private float price;
    private  String title;
    private int catalogId;
    private boolean status;


    //2 Get,Set

    public String getProducId() {
        return producId;
    }

    public void setProducId(String producId) {
        this.producId = producId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //3 Constructors
    public Product() {
    }

    public Product(String producId, String productName, float price, String title, int catalogId, boolean status) {
        this.producId = producId;
        this.productName = productName;
        this.price = price;
        this.title = title;
        this.catalogId = catalogId;
        this.status = status;
    }
    @Override
    public void inputData(List<Product> listProduc) {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = true;
        do {
            boolean isFit = true;
            System.out.println("Ma san pham:");
            String producId = scanner.nextLine();
          if (producId.length()>5){
              if (producId.startsWith("P")){
                  for (Product prod : listProduc) {
                      if (!prod.producId.equalsIgnoreCase(producId)){
                          isFit = false;
                      }
                  }
              }
          }
          if (!isFit){
              this.producId = producId;
              isExit=false;
          }else {
              System.out.println("Ma da ton tai hoac co loi, Vui long nhap lai.");
          }
        }while (isExit);

       isExit = true;
        do {
            boolean isDouble = false;
            System.out.println("Ten san pham:");
            String productName = scanner.nextLine();
            for ( Product element:listProduc) {
                if (element.productName.equalsIgnoreCase(productName)){
                    System.out.println("Ma da ton tai");
                    isDouble = true;
                }
            }
            if (!isDouble){
                this.productName = productName;
                isExit = false;
            }
        } while (isExit);

        do {
            System.out.println("Gia san pham:");
            this.price = Float.parseFloat(scanner.nextLine());
        }while (this.price<0);

        System.out.println("Tieu de:");
        this.title = scanner.nextLine();


        do {
            System.out.println("Trang trai: ");
            System.out.println("1. Da ban");
            System.out.println("2. Chua ban");
            byte choice = Byte.parseByte(scanner.nextLine());
            switch (choice){
                case 1:
                    this.status = true;
                    return;
                case 2:
                    this.status = false;
                    return;
                default:
                    System.out.println("Chon 1 hoac 2");
                    break;
            }
        }while (true);
    }

    public void listCategories(List<Categories> tList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Danh muc:");
            for (Categories cate: tList) {
                cate.displayData();
            }
            boolean isExit = true;
            do {
                this.catalogId = Integer.parseInt(scanner.nextLine());
                for (Categories cate: tList) {
                    if (cate.getCatalogId()==this.catalogId){
                        isExit = false;
                        break;
                    }else {
                        System.out.println("Ma ban chon khong ton tai. Vui long chon lai.");
                    }
                }
            }while (isExit);
    }

    /*public static Categories categoriesById(int categoriesId, List<Categories>categoriesList){
        for (Categories categories : categoriesList) {
            if (categories.getCatalogId()==categoriesId){
                return categories;
            }
        }
        return  null;
    }
    public  void addListCategoriesByData(List<Product>productList,List<Categories>categoriesList){
        for (Product product : productList) {
            Categories categories = categoriesById(product.getCatalogId(),categoriesList);
            if (categories!=null){
            categories.getProductArrayList().add(product);
            }
        }
    }*/

    @Override
    public void displayData() {
        String status = this.status?"Con hang":" Da Ban";

        System.out.printf("Ma ID: %3s - TenSp: %3s - Gia: %3.2f - Trang thai: %s \n", this.producId, this.productName, this.price,status  );
    }
}
