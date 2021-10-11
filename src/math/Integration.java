package math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Integration
{
    // apply simpson rule to approximately compute the integration.
    public double simpsonRule(double upper, double lower, int n, Function df) {
        BigDecimal result=new BigDecimal(0);

        BigDecimal unit =new BigDecimal((upper-lower)/n);
        BigDecimal factor1=unit.divide(new BigDecimal(3),10,RoundingMode.HALF_UP);
        BigDecimal[] x=new BigDecimal[n+1];

        for (int i = 0; i < x.length; i++)
        {
            x[i]=unit.multiply(new BigDecimal(i)).add(new BigDecimal(lower));
        }
        for (int i = 0; i < x.length; i++)
        {
            if(i==0 || i==x.length-1)
            {
                result=result.add(new BigDecimal(df.fun(x[i].doubleValue())));
            }
            else if(i%2 == 0)
            { // if i is even num.
                result=result.add(new BigDecimal(2*df.fun(x[i].doubleValue())));
            }
            else
                { // if i is odd num.
                    result=result.add(new BigDecimal(4*df.fun(x[i].doubleValue())));
            }
        }

        result=result.multiply(factor1);
        return result.doubleValue();
    }
    // compute the standard normal distribution integration
    // refer to the integration table in p382 of "probability and statistics" from ZheJiang University.
    public double stdGaussValue(double realUpper)
    {
        Integration integration = new Integration();
        double upper = 1.0;
        double lower = 0.0;
        int n = 200; // splited into 200 subintervals.
        // double realUpper = 0.03;

        if(realUpper >= 5.0)
        {
            return 1.0;
        }
        double result =
                integration.simpsonRule(upper, lower, n, new Function() {
                    @Override
                    public double fun(double x)
                    {
                        if(x==0)
                        {
                            return 0;
                        }
                        double t =  realUpper-(1-x)/x;
                        return Math.pow(Math.E, -0.5*t*t) / (x*x);
                    }
                });
        result /= Math.pow(2*Math.PI, 0.5);
        result = new BigDecimal(result).setScale(6, RoundingMode.HALF_UP).doubleValue(); // save 6 decimal places.
        return result;
    }
}