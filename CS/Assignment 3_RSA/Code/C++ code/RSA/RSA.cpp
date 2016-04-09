#include<iostream>
#include<algorithm>
#include<cmath>
using namespace std;

#define ll long long int

ll es[101];

ll modpow(ll a,ll b,ll mod)
{
    ll res=1;

    while(b>0)
    {
        if(b%2==0)
        {
            a=(a*a);
            a=a%mod;
            b=b/2;
        }
        else
        {
            res=(res*a);
            res=res%mod;
            b=b-1;

        }

    }
    return res;

}

bool chkprime(ll no)
{
    ll i;
    ll j=sqrt(no);

    for(i=2;i<=j;i++)
    {
        if(no%i== 0)
            return false;
    }
    return true;
}


ll gete(ll totient,ll n,ll p)
{
    ll cnt=-1,i;

    for(i=2;i<totient;i++)
    {
        if(totient%i==0)
            continue;
        if(chkprime(i)==true && i!=p)
        {
            if(__gcd(i,n)==1)
            {
                cnt=cnt+1;
                es[cnt]=i;
            }

            if(cnt>20)
                break;
        }

    }
    return cnt;

}

ll getd(ll e,ll totient)
{
    //cout<<"\n\nSSDFS:"<<totient;
    ll i,d;
    for(i=2;i<=100000001;i++)
    {
        d=(i*e)%totient;
        if(d==1)
        {
            return i;
        }
    }

}

void encrypt(ll m,ll e,ll n)
{
    ll encm;
    encm=modpow(m,e,n);

    cout<<encm;

}

void decrypt(ll c,ll d,ll n)
{
    ll decm;
    decm=modpow(c,d,n);

    cout<<decm;

}

int main()
{
    ll i,p,q,n;
    ll totient,e,d;

    cout<<"**************RSA_MENU******************";
    cout<<"\nEnter two prime numbers p and q :";
    cin>>p>>q;

    if(chkprime(p) ==false || chkprime(q) ==false || p==q )
    {
        cout<<"\nWRONG INPUT!!!!\n";
        exit(1);
    }

    n=p*q;
    cout<<"\nN:"<<n;
    totient=(p-1)*(q-1);
    cout<<"\nTot:"<<totient;
    e=gete(totient,n,p);

    cout<<"\nThe possible values of e are :\n";
    for(i=0;i<e;i++)
    {
        cout<<es[i]<<" ";
    }

    cout<<"\nEnter any value of e :";
    cin>>e;

    d=getd(e,totient);

    cout<<"\n\nPublic key is ("<<e<<","<<n<<")";
    cout<<"\nPrivate key is ("<<d<<","<<n<<")\n";

    ll ch,m,c;
    while(1)
    {

        cout<<"\n***************OPTIONS****************";
        cout<<"\n1.Encrypt a message\n2.Decrypt a message\n3.Exit\n(CHOICE):";
        cin>>ch;
        switch(ch)
        {

            case 1:
                cout<<"\nEnter the message to encrypt:";
                cin>>m;
                cout<<"\nEncrypted message:";
                encrypt(m,e,n);
                cout<<"\n";
                break;

            case 2:
                cout<<"\nEnter the message to decrypt:";
                cin>>c;
                cout<<"\nDecrypted message:";
                decrypt(c,d,n);
                cout<<"\n";
                break;

            case 3:return 0;

        }
    }


}
