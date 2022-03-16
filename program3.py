ItemSet={'Book','Chocolate','Medicine','Food'}
class Product:
    def __init__(self,name,count,price) -> None:
        self.tax=0
        self.name=name
        self.count=count
        self.price=price
        self.checkStatus(name)
        self.calculateTax()
        
    # for checking status of tax

    def checkStatus(self,name):
        temp=[]
        temp=name.split(' ')
        #whether it is imported
        self.imported= temp[0]=='Imported'

        #wheather it is in food, medicine or book
        self.taxExcluded=False
        for i in temp:
            if i in ItemSet:
                self.taxExcluded=True
                break

    # to calculate tax and new price
    def calculateTax(self):
        if(self.imported):
            self.tax+=self.price*0.05
        if(self.taxExcluded == False):
            self.tax+=self.price*0.1

        #new tax and price
        self.tax=round(self.tax,2)
        self.newPrice=round(self.tax + self.price,2)
        
    
    #to display value
    def itemPrint(self):
        print(self.name,":",self.count,":",self.newPrice)         
        
        

n=int(input("Enter number of items: "))
prodList=[]
for i in range(n):
    str1="Item "+str(i+1)+": "
    value=input(str1)
    value=value.split(':')
    for j in range(len(value)):
        value[j]=value[j].strip(' ')
    P=Product(value[0],int(value[1]),float(value[2]))
    prodList.append(P)
totalTax=0
totalPrice=0
for i in prodList:
    i.itemPrint()
    totalTax+=(i.tax*i.count)
    totalPrice+=(i.newPrice*i.count)
totalPrice=round(totalPrice,2)
totalTax=round(totalTax,2)
print("Tax : ",totalTax)
print("Total Price : ",totalPrice)