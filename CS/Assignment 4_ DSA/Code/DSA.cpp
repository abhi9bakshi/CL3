#include<iostream>
#include<cmath>
#include<cstdlib>


using namespace std;
#define ll long long int
#define ld long double

ll qdiv[10001];

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


bool prime_check(ll n)
{
    ll i;
    if(n==1)
        return false;

    if(n==2)
        return true;

    for(i=2;i<=sqrt(n);i++)
    {
        if(n%i==0)
            return false;
    }
    return true;

}



ll prime_div(ll p)
{
    ll i;
    ll cnt=0;
    for(i=2;i<p-1;i++)
    {
        if(prime_check(i))
        {
            ll chk=(p-1)%i;
            if(chk==0)
            {
                qdiv[cnt++]=i;
                if(cnt>10)
                    return cnt;
            }
        }
    }
    return cnt;
}

ll calc_g(ll p,ll q)
{
    ll i;
    for(i=2;i<p-1;i++)
    {
        ll power=(p-1)/q;
        ll temp=pow(i,power);
        if((temp % p)>1)
        {
            return temp;
        }

    }


}

ll powmod(ll a,ll b,ll c)
{
    ll i,ans=1;
    for(i=1;i<=b;i++)
    {
        ans=(ans*a)%c;
    }

    return ans%c;
}

ll getw(ll a, ll m)
{
    a = a%m;
    for (ll x=1; x<m; x++)
       if ((a*x) % m == 1)
          return x;
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


int main()
{
    ll q,p,g,x,y,k,r,s,hm;
    ll w,u1,u2,v,i;

    cout<<"Prime modulus p :";
    cin>>p;

    q=prime_div(p);
    q=qdiv[q-1];
    /*cout<<"\nValues of prime divisor can be : ";
    for(i=0;i<q;i++)
    {
        cout<<qdiv[i]<<" ";
    }*/
    cout<<"\nValue of q(prime divisor) :"<<q;

    //g=calc_g(p,q);
    cout<<"\n\nValue of g for given p & q :";
    cin>>g;

    cout<<"\n\nEnter the value of private key x less than q("<<q<<"): ";
    cin>>x;

    y=powmod(g,x,p);
    cout<<"\nPublic key y is :"<<y;

    cout<<"\n\n******************SIGNATURE CREATION********************";

    cout<<"\nEnter K :";
    cin>>k;
    ll kinv =getd(k,q);
    //k=rand()%q;
    //cout<<"\nK:"<<k;

    r=powmod(g,k,p)%q;
    cout<<"\n\nGenerated r :"<<r;
    cout<<"\n\nEnter the Hash of message :";
    cin>>hm;
    int lol = kinv * (hm+x*r);
    s=lol%q;
    cout<<"\n\nGenerated s :"<<s;

    cout<<"\n\nMessage with signature is sent!!!!";

    cout<<"\n\n******************SIGNATURE VERIFICATION********************";
    w=getd(s,q);
    cout<<"\n\nValue of w is:"<<w;
    cout<<"\n\nEnter the Hash of message :";
    cin>>hm;
    u1=(hm*w)%q;
    u2=(r*w)%q;

    cout<<"\nU1 U2:"<<u1<<" "<<u2;
    v=((modpow(g,u1,p)*modpow(y,u2,p))%p)%q;

    cout<<"\n\nValue of v is:"<<v;

    if(v==r)
        cout<<"\n\nSIGNATURE VERIFIED!!!!\n\n";
    else
        cout<<"\n\nSIGNATURE NOT VERIFIED!!!!\n\n";



}
