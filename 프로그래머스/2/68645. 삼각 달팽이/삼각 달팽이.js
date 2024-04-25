const DIRS = [[1,0],[0,1],[-1,-1],[1,0]];

function solution(n) {
  const arr = Array.from({length: n}, (v,i)=> new Array(i+1).fill(null));
  let dir = 0;
  let cur = 1;
  let x = 0, y = 0;
  const last = Math.floor((1 + n) * n / 2);  
  arr[0][0] = cur++;
    
  while(cur <= last){
      const nx = x + DIRS[dir][0];
      const ny = y + DIRS[dir][1];

      if((nx < 0 || nx >= n || ny < 0 || ny >= n) || arr[nx][ny])  {
         dir = (dir+1) % 4;
      } else {
         arr[nx][ny] = cur++;
         x = nx;
         y = ny;
      }  
  }

  return arr.flat()
}