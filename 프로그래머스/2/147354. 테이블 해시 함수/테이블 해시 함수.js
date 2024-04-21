function solution(data, col, row_begin, row_end) {
   return data
       .sort((a,b)=> {
        if(a[col-1] - b[col-1]!=0) return a[col-1] - b[col-1];
        else return  b[0] - a[0];
        })
       .map((v,i)=> v.reduce((acc,cur)=> acc + cur % (i+1),0))
       .filter((v,i)=> row_begin <= i+1 && i + 1 <= row_end)
       .reduce((acc,cur)=> cur^acc);
}