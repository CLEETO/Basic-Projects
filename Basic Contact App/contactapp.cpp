#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<ctype.h>
int count=-1,i=-1,m;
struct contact
{
   char fname[40],lname[40],no[40];
}p[100];
void add()
{
	FILE *ptr;
    ptr=fopen("D://contactapp.txt","a+");
	printf("Enter First Name: ");
    scanf("%s",p[i].fname);
    printf("Enter Last Name: ");
    scanf("%s",p[i].lname);
    printf("Enter Mobile Number: ");
    scanf("%s",p[i].no);
    fprintf(ptr,"%s %s %s\n",p[i].fname,p[i].lname,p[i].no);
    rewind(ptr);
    count=-1,i=-1;
    while(feof(ptr)==0)
    {
   	    count++;i++;
   	    fscanf(ptr,"%s%s%s",p[i].fname,p[i].lname,p[i].no);
    }
    printf("Filled %d out 100.\n",count);
    fclose(ptr);
}
void search(int n)
{
	FILE *ptr;
    ptr=fopen("D://contactapp.txt","a+");
    if(n==1)
	printf("Enter search contact: ");
	char se[40];
	if(n==1)
	scanf("%s",se);
	int f=0;
	for(int j=0;j<i;j++)
	{
		
		if(n==1)
		{		
		if(strcmpi(se,p[j].fname)==0||strcmpi(se,p[j].lname)==0||strcmpi(se,p[j].no)==0)
		{
			f=1;
			printf("%s %s : %s\n",p[j].fname,p[j].lname,p[j].no);
		}
   	    }
   	    else
   	    printf("%s %s : %s\n",p[j].fname,p[j].lname,p[j].no);
				
	} 
	if(n==1)
	if(f==0) printf("Contact not found\n"); 
	fclose(ptr);
}
edit(int k)
{
	FILE *ptr;
	printf("Enter contact first name: ");
	char se[40];
    scanf("%s",se);
	int f=0;
	for(int j=0;j<i;j++)
	{
				
		if(strcmpi(se,p[j].fname)==0)
		{
			f=1;
			m=j;
			printf("%s %s : %s\n",p[j].fname,p[j].lname,p[j].no);
		}
				
	} 
	if(f==0) printf("Contact not found\n"); 
	if(f==1)
	{
		if(k==0)
		{
		  printf("Enter new details: \n");
		  printf("Enter First Name: ");
          scanf("%s",p[m].fname);
          printf("Enter Last Name: ");
          scanf("%s",p[m].lname);
          printf("Enter Mobile Number: ");
          scanf("%s",p[m].no);
          ptr=fopen("D://contactapp.txt","w");
          for(int j=0;j<i;j++)
            fprintf(ptr,"%s %s %s\n",p[j].fname,p[j].lname,p[j].no);
          fclose(ptr);  
          ptr=fopen("D://contactapp.txt","a+");
          count=-1,i=-1;
          while(feof(ptr)==0)
         {
   	       count++;i++;
   	       fscanf(ptr,"%s%s%s",p[i].fname,p[i].lname,p[i].no);
         }
          fclose(ptr);
          printf("Contact edited.\n");
        }
        if(k==1)
        {
           ptr=fopen("D://contactapp.txt","w");
           for(int j=0;j<i;j++)
             if(j!=m) fprintf(ptr,"%s %s %s\n",p[j].fname,p[j].lname,p[j].no);
           fclose(ptr);  	
           ptr=fopen("D://contactapp.txt","a+");
          count=-1,i=-1;
          while(feof(ptr)==0)
         {
   	       count++;i++;
   	       fscanf(ptr,"%s%s%s",p[i].fname,p[i].lname,p[i].no);
         }
         fclose(ptr);
         printf("Contact deleted.\n");
		}
	}
}
int main()
{
	
   FILE *ptr;
   ptr=fopen("D://contactapp.txt","a+");
   
   while(feof(ptr)==0)
   {
   	  count++;i++;
   	  fscanf(ptr,"%s%s%s",p[i].fname,p[i].lname,p[i].no);
   }
   printf("Filled %d out 100.\n",count);
   fclose(ptr);
   char ch,ys='y';
   while(ys=='y'||ys=='Y')
   {
   	  printf("+ Add Contact   S Search contact   E Edit contact   D Delete contact   V View all contacts\nEnter your choice: ");
      scanf(" %c",&ch);
      
   	  switch(toupper(ch))
   	  {
   	  	 case '+':
   	  	 {
   	  	 	add();
   	  	 	break;
   	     }
   	     case 'S':
		 {
		 	
		    search(1);
			break;	
	     }
	     case 'V':
		 {
		 	
		    search(0);
			break;	
	     }
		 case 'E':
	     {
	        edit(0);
	        break;
		 } 
		 case 'D':
		 {
		    edit(1);
			break;	
		 }	
		 default:
		 {
		 	printf("Invalid Entry\n");
		    break;
	     }
	  }
	  k:
	  printf("Do you want to continue? (Y/N): ");
      scanf(" %c",&ys);
      if(ys!='y'&&ys!='Y'&&ys!='n'&&ys!='N')
        goto k;
   }
   return 0;
}	
