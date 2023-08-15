package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.*;

public class ShopManagement {
    static  List<Categories> categoriesList = new ArrayList<>();

   static List<Product>productList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        miniDataCate();
        miniDataProduc();
        categorieStatus();
        do {
            menuShopManager();
            byte choice = Byte.parseByte(scanner.nextLine());
            switch (choice){
                case 1:
                    CatalogManager(scanner);
                    break;
                case 2:
                    producManager(scanner);
                    break;
                case 0:
                    System.exit(0);
                    break;
                default: break;
            }
        }while (true);

    }

    public static void CatalogManager(Scanner scanner){
        boolean isExit = true;
        do {
            menuCatalogManager();
            byte choice = Byte.parseByte(scanner.nextLine());
            switch (choice){
                case 1:
                    Categories categories = new Categories();
                    categories.inputData(categoriesList);
                    categoriesList.add(categories);
                    System.out.println("Da them xong");
                    break;
                case 2:
                    Iterator<Categories> categoriesIterator  = categoriesList.iterator();
                    while (categoriesIterator.hasNext()){
                        Categories categories1 = categoriesIterator.next();
                        categories1.displayData();
                    }
                    break;
                case 3:
                    System.out.println("Ma danh muc Can cap nhap:");
                    int catalogId = Integer.parseInt(scanner.nextLine());
                    boolean isSearch =false;
                    Iterator<Categories> categoriesIterator2  = categoriesList.iterator();
                    while (categoriesIterator2.hasNext()){
                        Categories categories2 = categoriesIterator2.next();
                        int testId = categories2.getCatalogId();
                        if (catalogId ==testId){
                            System.out.printf("Ten Cu: $s\n",categories2.getCatalogName());
                            System.out.println("Cap nhap lai ten:");
                            String newName = scanner.nextLine();
                            categories2.setCatalogName(newName);
                            System.out.println("Da cap nhap xong.");
                            isSearch = true;
                            break;
                        }
                    }
                        if (!isSearch){
                            System.out.println("Ma ban tim  kiem khong co");
                        }
                    break;
                case 4:
                    System.out.println("Nhap ma Danh muc muon xoa: ");
                    int delCategories = Integer.parseInt(scanner.nextLine());
                    Iterator<Categories> categoriesIterator3  = categoriesList.iterator();
                    boolean isDel = false;
                    while (categoriesIterator3.hasNext()){
                        Categories categories3 = categoriesIterator3.next();
                        int test = categories3.getCatalogId();
                        if ( test== delCategories){

                            if (categories3.getProductArrayList()!=null){
                                categoriesList.remove(categories3);
                                isDel = true;
                            }else {
                                System.out.println("Danh muc dang chua san pham. khong xoa dc.");
                            }
                            break;
                        }
                    }
                    if (!isDel){
                        System.out.println("Khong ton tai Ma danh muc");
                    }
                    break;
                case 5:
                    isExit = false;
                    break;
            }
        }while (isExit);
    }
    public static void  producManager(Scanner scanner){
        boolean isExit = true;
        do {
        menuProducManager();
        byte choice = Byte.parseByte(scanner.nextLine());
        switch (choice){
            case 1:
                Product product = new Product();
                product.inputData(productList);
                product.listCategories(categoriesList);
                productList.add(product);
                System.out.println("Da them xong!");
                break;
            case 2:
                Iterator<Product> productIterator = productList.iterator();
                while (productIterator.hasNext()){
                    Product product1 = productIterator.next();
//                    System.out.println(product1);
                    product1.displayData();
                }
                break;
            case 3:
                System.out.println("Nhap ma san pham");
                String productName = scanner.nextLine();
                for (Product product3 : productList
                     ) {
                    if (product3.getProducId().toLowerCase().equals(productName.toLowerCase())){
                        System.out.printf("Gia ban cu: %.2f \n", product3.getPrice());
                        System.out.println("Nhap gia ban moi: ");
                        float newPrice = Float.parseFloat(scanner.nextLine());
                        product3.setPrice(newPrice);
                    }
                }
                System.out.println("Da cap nhap xong");
                break;
            case 4:
                System.out.println("Nhap ma san pham muon xoa: ");
                String delName = scanner.nextLine();
                Iterator<Product> productIterator1 = productList.iterator();

                while (productIterator1.hasNext()){
                    Product product1 = productIterator1.next();
                    if (product1.getProducId().toLowerCase().contains(delName.toLowerCase())){
                        productList.remove(product1);
                    }else {
                        System.out.println("Ma san pham khong ton tai.");
                    }
                }
                break;
            case 5:
                productList.sort(new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return (int) (o1.getPrice()-o2.getPrice());
                    }
                });
                break;
            case 6:
                productList.sort(new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getProductName().compareTo(o2.getProductName());
                    }
                });
                break;
            case 7:
//               Set<String>listCategories = new HashSet<>();
                for (Categories cate : categoriesList) {
                int quantity =0;
                    for (Product pro : productList) {
                        if (pro.getCatalogId()==cate.getCatalogId()){
                            quantity+=1;
                        }
                    }
                        System.out.printf("%3s : %d \n", cate.getCatalogName(),quantity);
                }
                break;
            case 8:
                System.out.println("Nhap ten can tim kiem: ");
                String searchName = scanner.nextLine();
                Iterator<Product> productIterator2 = productList.iterator();
                while (productIterator2.hasNext()){
                    Product product1 = productIterator2.next();
                    if (product1.getProductName().toLowerCase().contains(searchName.toLowerCase())){
                        product1.displayData();
                        break;
                    }
                }
                break;
            case 9:
                isExit = false;
                break;

        }
        }while (isExit);
    }

    public static void categorieStatus (){
        for (Categories categoies : categoriesList) {
            int quantity =0;
            for (Product pro : productList) {
                if (pro.getCatalogId()==categoies.getCatalogId()){
                    quantity+=1;
                }
            }
            if (quantity==0){
                categoies.setStatus(false);
            }else {
                categoies.setStatus(true);
            }
        }
    }

    public static void menuShopManager(){
        System.out.println("*************************SHOP MANAGEMENT***************");
        System.out.println("1. Quản lý danh mục sản phẩm");
        System.out.println("2. Quản lý sản phẩm");
        System.out.println("3. Thoát");
    }
    public static void menuCatalogManager(){
        System.out.println("***************** CATALOG MANAGEMENT**************");
        System.out.println("1. Thêm mới danh mục");
        System.out.println("2. Hiển thị thông tin các danh mục");
        System.out.println("3. Cập nhật tên danh mục theo mã danh mục");
        System.out.println("4. Xóa danh mục theo mã danh mục");
        System.out.println("5. Thoát ");
    }
    public static void menuProducManager(){
        System.out.println(" ***************** PRODUCT MANAGEMENT**************");
        System.out.println("1. Thêm mới sản phẩm ");
        System.out.println("2. Hiển thị thông tin sản phẩm");
        System.out.println("3. Cập nhật giá sản phẩm theo mã sản phẩm");
        System.out.println("4. Xóa sản phẩm theo mã sản phẩm");
        System.out.println("5. Sắp xếp sản phẩm theo giá sản phẩm tăng dần");
        System.out.println("6. Sắp xếp sản phẩm theo tên tăng dần");
        System.out.println("7. Thống kê số lượng sản phẩm theo danh mục sản phẩm");
        System.out.println("8. Tìm kiếm sản phẩm theo tên sản phẩm");
        System.out.println(" 9. Thoát (Quay lại Shop Management)");

    }


    //Data-Test
    public static void miniDataCate(){
        Categories categories = new Categories(01, "Hoa Qua",false);
        categoriesList.add(categories);
        categories = new Categories(02, "Bia Ruou",true);
        categoriesList.add(categories);
        categories = new Categories(03, "Quan ao",true);
        categoriesList.add(categories);
        categories = new Categories(04, "Phu kien",false);
        categoriesList.add(categories);
        categories = new Categories(05, "Giay dep",true);
        categoriesList.add(categories);
    }
    public  static  void miniDataProduc(){
        Product product = new Product("P10001","Tao",10,"tao Ngot",01,true);
        productList.add(product);
        product = new Product("P10002","Le",20,"Le Ngot",01,false);
        productList.add(product);
        product = new Product("P10003","Asahi",30,"Japan",02,true);
        productList.add(product);
        product = new Product("P10004","Sapolo",40,"Japan",02,false);
        productList.add(product);
        product = new Product("P10005","Polo Nam",50,"China",03,true);
        productList.add(product);
        product = new Product("P10006","Vay Nu",60,"china",03,false);
        productList.add(product);
        product = new Product("P10007","Dong Ho",70,"Rolex",04,true);
        productList.add(product);
        product = new Product("P10008","Nuoc hoa",80,"GUCCI",04,false);
        productList.add(product);
        product = new Product("P10009","Adidas",90,"Yokohama",05,true);
        productList.add(product);
        product = new Product("P10010","Puma",100,"Osaka",05,false);
        productList.add(product);


    }
}
