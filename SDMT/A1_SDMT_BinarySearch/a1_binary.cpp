#include <iostream>
#include<string.h>
#include<fstream>
using namespace std;

#define IN 1
#define OUT 0

class Buk
{
public:
  int bid;
  char bname[25] ;
  char author[25];
  int noofcopies;
  int status;
}book;

FILE *librecord;

void insert();
void update();
void SearchBook();
void request();
void DisplayBook();

char info[500];

void insert()
{
    int i;
    book.status=IN;
    //opening the librecord file
    librecord = fopen("librecord.txt","a+");
    cout<<"Enter The uniqueid of The Book :(Integer): ";
    cin>>book.bid;
    cout<<"\nEnter The Name of The Book :";
    cin>>book.bname;
    cout<<"\nEnter The Name of Author :";
    cin>>book.author;
    cout<<"\nEnter The Number of copies Of The Book:(Integer) :";
    cin>>book.noofcopies;
    fprintf(librecord,"\n%d\t%s\t%s\t%d\t",book.bid,book.bname,book.author,book.noofcopies);
    fclose(librecord);
    cout<<"\n A New Book has been Added Successfully...\n";

}

void SearchBook()
{
    int i;
    char Target[25],stats[3];
    int Found=0;
    if((librecord=fopen("librecord.txt","r"))==NULL)
      cout<<" ! The File is Empty...\n\n";
    else
    {
        cout<<"\nEnter The Name Of Book : ";
        cin>>Target;
        while(!feof(librecord)&& Found==0)
        {
        fscanf(librecord,"%d %s %s %d %d", &book.bid,book.bname,book.author,&book.status,&book.noofcopies);
            if(strcmp(Target,book.bname)==0)
                Found=1;
            
        }
        if(Found)
        {
            if(book.status==IN)
                strcpy(stats,"IN");
            else
                strcpy(stats,"OUT");
           
            cout<<"\nThe Unique ID of The Book: " <<book.bid<<"\nThe Name of Book is: "<< book.bname<<"\nThe Author is: "<<book.author<<" \nThe Book Status: "<<stats;
            }
        else if(!Found)
        cout<<"! There is no such Entry...\n";
        fclose(librecord);
    }

}

void DisplayBook()
{
    librecord = fopen("librecord.txt","a+");
    cout<<"\nBookid\tName\tAuthor\tCopies\n";
    do
    {
        fgets(info,500,librecord);
       cout<<info;
    }while(!feof(librecord));
    fclose(librecord);
    
   } 


int main() 
{
	string uname,pwd;
	int ch;

	cout << "\t \n Library management System \n" ; 
	cout<<"\n Enter Username : ";
	cin>>uname;
	cout<<"\n Enter Password : ";
	cin>>pwd;


	if(uname=="a" && pwd=="a")
	{
	cout<<"\nYou are logged in as Librarian !!! \n";
		
	while(1)
	{
	cout<<"\n\n 1.Insert Book";
	cout<<"\n 2.Display Book";
	cout<<"\n 3.Search Book";
	cout<<"\n 4.log out";
	cout<<"\n Enter your choice ???";
	cin>>ch;

		switch(ch)
		{
		case 1:
		insert();
		break;

		case 2:
		DisplayBook();
		break;
		
		case 3:
		SearchBook();
		break;
	
		case 4:
		cout<<"\n You have log out Successfully...!\n";
		return 0;
		break;

		default:
		cout<<"Invalid choice...!";
		}
		
	}
	}

	if(uname=="s" && pwd=="s")
	{
	cout<<"\nYou are logged in as Student !!! \n\n";
	while(1)
	{
	cout<<"\n 1.Search Book";
	cout<<"\n 2.log out\n";
	cout<<"\n Enter your choice ???";
	cin>>ch;

		switch(ch)
		{
		case 1:
		SearchBook();
		break;
		
		case 2:
		cout<<"\n You have log out Successfully...!\n";
		return 0;

		default:
		cout<<"Invalid choice...!\n";
		}
	}	
	}

	
		
	
	return 0;
}