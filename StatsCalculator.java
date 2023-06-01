import java.util.Arrays;

public class StatsCalculator {
    private double[] values;
    private double[] sortedValues;
    public StatsCalculator(){
        values = new double[20];
        sortedValues = new double [20];
    }

    public StatsCalculator(double[] values){
        this.values = Arrays.copyOf(values, values.length);
        sortedValues = new double[values.length];
    }
    public void sortData(){
        sortedValues = Arrays.copyOf(values, values.length);
        Arrays.sort(sortedValues);
    }
    /**
     * Find the maximum value in an array
     *
     * @param
     * @return maximum value of array or Integer.MIN_VALUE if array is empty
     */
    public double CalculateMax() {
        double max = values[0];
        for (int i = 1; i < values.length; i++){
            if (values[i] > max){
                max = values[i];
            }
        }
        return max;
    }

    /**
     * Find the minimum value in an array
     *
     * @param
     * @return maximum value of array of Integer.MAX_VALUE if array is empty
     */
    public  double CalculateMin() {
        double min = values[0];
        for (int i = 1; i < values.length; i++){
            if (values[i] < min){
                min = values[i];
            }
        }
        return min;
    }

    /**
     * Find the sum of the array by going through each value of the array and adding them together
     *
     * @param
     * @return sum value of array
     */
    public double CalculateSum() {
        double sum = 0;
        for (double value : values){
            sum += value;
        }
        return sum;
    }

    /**
     * Calculate the average of the array by using the method of CalculateMean / by the length of the array.
     *
     * @param
     * @return average value of array
     */
    public double calculateMean() {
        double sum  =  CalculateSum();
        return sum / values.length;
    }

    /**
     * Calculate the median of the array by getting the middle number in the array and if the array is even then
     * I would get the number before the median and after the median / 2
     *
     * @param
     * @return median value of the array
     */
    public double CalculateMedian(){
        sortData();
        int median = values.length / 2;
        if (values.length % 2 == 0) {
            return (sortedValues[median - 1] + sortedValues[median]) / 2.0;

        }
        return sortedValues[median];
    }

    /**
     * Calculate the first quartile
     *
     * @return first quartile of the array
     */
    public double CalculateFirstQuartile(){
        sortData();
        int length = sortedValues.length;
        int mid = length / 2;

        double[] firstHalf;
        if(length % 2 == 0){
            firstHalf = Arrays.copyOfRange(sortedValues, 0, mid);
        }
        else{
            firstHalf = Arrays.copyOfRange(sortedValues, 0, mid + 1);
        }

        int firstHalfLength = firstHalf.length;
        if (firstHalfLength % 2 == 0) {
            int midIndex = firstHalfLength / 2;
            return (firstHalf[midIndex - 1] + firstHalf[midIndex]) / 2.0;
        } else {
            return firstHalf[firstHalfLength / 2];
        }

    }




    /**
     * Calculate the third quartile
     *
     * @return third quartile of the array
     */
    public double CalculateThirdQuartile(){
       sortData();
       int index = 3* (values.length - 1) /4;
       double indexFraction = (3 * (values.length - 1) % 4) / 4.0;

       if (indexFraction == 0) {
           return sortedValues[index];
       }
       else{
           return sortedValues[index] + (sortedValues[index + 1] - sortedValues[index]) * indexFraction;
       }
    }
    public double findOutliers(){
        double q1 = CalculateFirstQuartile();
        double q3 = CalculateThirdQuartile();
        double iqr = q3 - q1;
        double lower = q1 - 1.5 * iqr;
        double upper = q3 + 1.5 * iqr;

        int count = 0;
        for(double value : values){
            if(value < lower || value > upper){
                count++;
                return value;
            }
        }
        return count;
    }
    /**
     *Print the orgin data
     */
    public void print() {
        System.out.print("Your data is: ");
        for (double value : values) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    /**
     *Print the sorted data
     */
    public void printSorted() {
        sortData();
        System.out.print("Your sorted data is: ");
        for (double value : sortedValues) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public void printFiveNumberSummary() {
        sortData();
        double min = CalculateMin();
        double q1 = CalculateFirstQuartile();
        double median = CalculateMedian();
        double q3 = CalculateThirdQuartile();
        double max = CalculateMax();
        double out = findOutliers();

        System.out.println("The five number summary is: ");
        System.out.println("Min: " + min);
        System.out.println("Q1: " + q1);
        System.out.println("Median: " + median);
        System.out.println("Q3: " + q3);
        System.out.println("Max: " + max);
        System.out.println(("Number of outlier: " + out));
    }
}