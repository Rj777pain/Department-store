import java.util.*;
public class DepartmentStoreItem{
    private double tax=0;
    private String productName;
    private int productCount;
    private double initialPrice;
    private double finalPrice=0;

    //set of items to be excluded from sales tax
    Set<String> itemToBeExcluded = new HashSet<String>(Arrays.asList("book","chocolate bar","chocolate","medicine","food"));

    public DepartmentStoreItem(String productDetails){
        String[] detailsList = productDetails.split(" : ");
        this.productName = detailsList[0];
        this.productCount = Integer.parseInt(detailsList[1]);
        this.initialPrice = Double.parseDouble(detailsList[2]);
    }

    public void calculateTax(){
        String nameInLowerCase = productName.toLowerCase();

        if(isImported(nameInLowerCase)){
            nameInLowerCase = getTruncatedName(nameInLowerCase);           
            tax+=initialPrice*0.05;
        }
        if(isSalesTaxApplicable(nameInLowerCase))
            tax+=initialPrice*0.1;
        finalPrice=initialPrice+tax;
        
    }

    private boolean isSalesTaxApplicable(String name){    
        return (!itemToBeExcluded.contains(name));
    }
    private boolean isImported(String name){   
        return name.contains("imported");
    }

    //removing imported tag
    public String getTruncatedName(String name){
        return(name.replaceAll("imported ",""));
    }
    //getteres
    public double getFinalPrice(){
        return(finalPrice);
    }
    public String getProductName(){
        return(productName);
    }
    public double getTotalTaxOfItem(){
        return(productCount*tax);
    }
    public double getTotalPriceOfItem(){
        return(finalPrice*productCount);
    }

    public String productInitialPriceDetails(){
        return(productName+':'+productCount+':'+initialPrice);
    }
    public String productFinalPriceDetails(){
        return(productName+':'+productCount+':'+finalPrice);
    }


    public static void main(String[] args){
        List<DepartmentStoreItem> departmentStoreItems=new ArrayList<>();
        double totalPrice=0,totalTax=0;

        departmentStoreItems.add(new DepartmentStoreItem("Book : 1 : 12.49"));
        departmentStoreItems.add(new DepartmentStoreItem("Music CD : 1 : 14.99"));
        departmentStoreItems.add(new DepartmentStoreItem("Chocolate Bar : 1 : 0.85"));
        
        System.out.println("Input");
        for(DepartmentStoreItem item:departmentStoreItems){
            System.out.println(item.productInitialPriceDetails());
        }
        System.out.println("\nOutput");
        for(DepartmentStoreItem item:departmentStoreItems){
            item.calculateTax();
            System.out.println(item.productFinalPriceDetails());
            totalTax+=item.getTotalTaxOfItem();
            totalPrice+=item.getTotalPriceOfItem();
        }
        System.out.println("\nTax : "+totalTax);
        System.out.println("Total Price : "+totalPrice);
    }
}

