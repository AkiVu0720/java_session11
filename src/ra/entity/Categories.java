package ra.entity;

import ra.IShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Categories implements IShop<Categories> {
    //1. Attribute
    private int catalogId;
    private String catalogName;
    private boolean status;
    private  List<Product>productArrayList;

    public List<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(List<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    //2. Get,Set.

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    //3. Constructors
    public Categories() {
    }

    public Categories(List<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }

    public Categories(int catalogId, String catalogName, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.status = status;
    }

    //4 Method
    @Override
    public void inputData(List<Categories> list) {
        Scanner scanner = new Scanner(System.in);
        boolean isExitId = true;
        do {
            boolean isDouble = false;
            System.out.println("Mã Danh Mục");
            int cateId = Integer.parseInt(scanner.nextLine());
            for ( Categories element:list) {
                if (element.getCatalogId()==cateId){
                    System.out.println("Ma da ton tai");
                    isDouble = true;
                }
            }
            if (!isDouble){
                this.catalogId = cateId;
                isExitId = false;
            }
        } while (isExitId);

        isExitId = true;
        do {
            boolean isDouble = false;
            System.out.print("Tên Danh Mục");
            String catalogName = scanner.nextLine();
            for ( Categories element:list) {
                if (element.getCatalogName().equalsIgnoreCase(catalogName)){
                    System.out.println("Ma da ton tai");
                    isDouble = true;
                }

            }
            if (!isDouble){
                this.catalogName = catalogName;
                isExitId = false;
            }
        } while (isExitId);

    }


    @Override
    public void displayData() {
        String statusText = this.status?"Ton Kho":"Het Hang";
        System.out.printf("Mã danh mục: %2d -  Danh mục: %2s - Trang Thai: %3s  \n ", this.catalogId, this.catalogName, statusText);
    }



}
