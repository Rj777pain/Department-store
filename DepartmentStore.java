import java.util.*;
public class DepartmentStore{
    private double tax=0;
    private String productName;
    private int productCount;
    private double initialPrice;
    private double finalPrice=0;

    Set<String> itemToBeExcluded = new HashSet<String>(Arrays.asList("book","chocolate bar","chocolate","medicine","food"));

    public DepartmentStore(String productDetails){
        String[] detailsList=productDetails.split(" : ");
        this.productName=detailsList[0];
        this.productCount=Integer.parseInt(detailsList[1]);
        this.initialPrice=Double.parseDouble(detailsList[2]);
        this.calculateTax();
    }

    private void calculateTax(){
        String name=productName.toLowerCase();
        

        if(isImported(name)){

            name=name.replaceAll("imported ","");
            
            tax+=initialPrice*0.05;
        }
        if(isSalesTaxApplicable(name))
            tax+=initialPrice*0.1;
        finalPrice=initialPrice+tax;
        
    }
    private boolean isSalesTaxApplicable(String name){    
        return (!itemToBeExcluded.contains(name));
    }
    private boolean isImported(String name){
        
        return name.contains("imported");
    }

    public double getFinalPrice(){
        return(finalPrice);
    }
    public String getName(){
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
        List<DepartmentStore> departmentStoreItems=new ArrayList<>();
        departmentStoreItems.add(new DepartmentStore("Book : 1 : 12.49"));
        departmentStoreItems.add(new DepartmentStore("Music CD : 1 : 14.99"));
        departmentStoreItems.add(new DepartmentStore("Chocolate Bar : 1 : 0.85"));
        double totalPrice=0,totalTax=0;
        System.out.println("Input");
        for(DepartmentStore item:departmentStoreItems){
            System.out.println(item.productInitialPriceDetails());
        }
        System.out.println("\nOutput");
        for(DepartmentStore item:departmentStoreItems){
            System.out.println(item.productFinalPriceDetails());
            totalTax+=item.getTotalTaxOfItem();
            totalPrice+=item.getTotalPriceOfItem();
        }
        System.out.println("\nTax : "+totalTax);
        System.out.println("Total Price : "+totalPrice);
    }
}

