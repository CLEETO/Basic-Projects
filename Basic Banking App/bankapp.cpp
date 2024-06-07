#include<stdio.h>
#include<stdlib.h>
#include<string.h>
int admink=1234,acclast=1000;
struct stat 
{
	char type1[7],type2[12],type3[5];
	int amount;
	float bal;
	struct stat *link1;
};
struct name
{
	char name[20];
	int accno,pass;
	float bal;
	struct name *link;
	struct stat *head;
};
struct name *head1,*t,*prev;
void addname()
{
	printf("Enter admin key: ");
	int k;
	scanf("%d",&k);
	if(k!=admink) { printf("Invalid Admin key.\n\n"); return; }
	struct name *ptr=(struct name*)malloc(sizeof(struct name));
	printf("Enter name: ");
	scanf(" %[^\n]",ptr->name);
	ptr->accno=acclast+1,acclast++;
	printf("Enter new PIN: ");
	scanf("%d",&ptr->pass);
	printf("New account number: %d\n",ptr->accno);
	if(head1==NULL) head1=ptr;
	else
	{
	t=head1;
	while(t->link!=NULL) t=t->link;
	t->link=ptr;
    }
	printf("Account added\n\n");
}
void searchacc(int x)
{
	t=head1;
	while(t!=NULL) 
	{
	   if(t->accno==x) break;
	   prev=t,t=t->link; 
    }
}
void remname()
{
	if(head1==NULL) { printf("No accounts added.\n\n"); return; }
    printf("Enter admin key: ");
	int k,acc;
	scanf("%d",&k);
	if(k!=admink) { printf("Invalid Admin key.\n\n"); return; }	
	printf("Enter account number: ");
	scanf("%d",&acc);
	searchacc(acc);
	if(t==NULL) { printf("Account not found\n\n"); return; }
	if(t==head1) head1=NULL; 
	else prev->link=t->link;
	printf("Account Deleted\n\n");    
}
void state(char t1[],char t2[],char t3[],int amt,float b)
{
	struct stat *ptr=(struct stat*)malloc(sizeof(struct stat));
	strcpy(ptr->type1,t1); 
	strcpy(ptr->type2,t2);
	strcpy(ptr->type3,t3);
	ptr->amount=amt;
	ptr->bal=b;
	if(t->head==NULL)  { t->head=ptr; return; }
	struct stat *k=t->head;
	while(k->link1!=NULL)
	  k=k->link1;
	if(k->link1==NULL) k->link1=ptr;
    
}
void banking()
{
	if(head1==NULL) { printf("No accounts added.\n\n"); return; }
	printf("Enter account number: ");
	int acc,pin;
	float amt;
	scanf("%d",&acc);
	searchacc(acc);
	if(t==NULL) { printf("Account not found\n\n"); return; }
	printf("Enter PIN: ");
	scanf("%d",&pin);
	if(pin!=t->pass) { printf("PIN Wrong\n\n"); return; }
	printf("1.Get account details    2.Credit    3.Debit    4.Transfer    5.Statement    6.Exit\n");
	int ch;
	printf("Enter choice: ");
	scanf("%d",&ch);
	while(ch>0)
	{
		searchacc(acc);
		switch(ch)
		{
			case 1:
			{
			   printf("Name: %s\tAccNo: %d\tBal: %f\n\n",t->name,t->accno,t->bal);
			   break;		
			}
			case 2: 
			{
			   printf("Enter amount: ");
			   scanf("%f",&amt);
			   t->bal+=amt;
			   printf("Amount credited\n");
			   char q1[7]="CREDIT",q2[12]="CASH",q3[5]="SELF";
			   state(q1,q2,q3,amt,t->bal);
			   break;
			}
			case 3: 
			{
			   printf("Enter amount: ");
			   scanf("%f",&amt);
			   if(amt>t->bal) { printf("Insuficient balance\n\n"); break; }
			   t->bal-=amt;
			   printf("Amount Debited\n");
			   char q1[7]="DEBIT",q2[12]="CASH",q3[5]="SELF";
			   state(q1,q2,q3,amt,t->bal);
			   break;
			}
		    case 4: 
			{
			   struct name *t1=t;
			   printf("Enter account number to transfer to: ");
			   int k;
			   scanf("%d",&k);
			   searchacc(k);
			   if(t==NULL) { printf("Account not found\n\n"); break; }
			   printf("Enter amount: ");
			   scanf("%f",&amt);
			   if(amt>t1->bal) { printf("Insuficient balance\n\n"); break; }
			   t1->bal-=amt;
			   t->bal+=amt;
			   printf("Amount Transfered\n\n");
			   char q3[5];
			   sprintf(q3,"%d",t->accno);
			   char q1[7]="DEBIT",q2[12]="TRANSFER";
			   struct name *h=t;
			   t=t1;
			   state(q1,q2,q3,amt,t->bal);
			   t=h;
			   char q4[7]="CREDIT",q5[12]="TRANSFER",q6[5];
			   sprintf(q6,"%d",t1->accno);
			   state(q4,q5,q6,amt,t->bal);
			   t=t1;
			   break;
			}
			case 5:
			{
			   struct stat *ptr1=t->head;
			   if(ptr1==NULL) { printf("No Transaction.\n\n"); break; }
			   while(ptr1!=NULL)
			   {
			   	 if(strcmp(ptr1->type1,"DEBIT")==0)
		           printf("Type: %s   Mode: %s   To: %s   Amt: %d    Bal: %f\n",ptr1->type1,ptr1->type2,ptr1->type3,ptr1->amount,ptr1->bal);
		         else
		           printf("Type: %s   Mode: %s   From: %s   Amt: %d    Bal: %f\n",ptr1->type1,ptr1->type2,ptr1->type3,ptr1->amount,ptr1->bal);
			     ptr1=ptr1->link1;
			   }
			   printf("\n");
			   break;
			}
			case 6: return; break;
			default: printf("Invalid Entry\n\n");
		}
		printf("Enter choice: ");
	    scanf("%d",&ch);
	}
	printf("Exited from account\n\n"); 
}
int main()
{
	printf("1. Add Account    2. Banking    3. Delete account    4.Exit\n");
	int ch;
	printf("Enter choice: ");
	scanf("%d",&ch);
	while(ch>0)
	{
	switch(ch)
	{
		case 1: addname(); break;
		case 2: banking(); printf("\n"); break;
		case 3: remname(); break;
		case 4: exit(0);   break;
		default: printf("Invalid Entry\n\n");
	}
	printf("1. Add Account    2. Banking    3. Delete account    4.Exit\n");
	printf("Enter choice: ");
	scanf("%d",&ch);
    }
}
