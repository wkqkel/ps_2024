

function solution(storey) {
   const str = [0,...String(storey)].map(Number);
  
   for(let i = str.length - 1 ; i > 0; i--){
       if(str[i] == 5 && str[i-1] < 5) continue;
       if(str[i] >= 5) {
           str[i] = 10 - str[i];
           str[i-1]++;
       }
   }

    return str.reduce((a,b)=> a + b);
}