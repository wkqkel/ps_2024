


function solution(book_time) {
   const bt = book_time.map((time)=> {
       let st = time[0].split(":").map(Number);
       let en = time[1].split(":").map(Number);
       st = st[0] * 60 + st[1];
       en = en[0] * 60 + en[1];
       return [st, en]
   }).sort((a,b)=> a[0]-b[0])
    // 최대 겹치는 구간 몇갠지
    
    const rooms = [];
    let mx = -1
    for(let i = 0; i< bt.length; i++){
        const cur = bt[i];
        for(let j = 0; j < rooms.length; j++){
            if(rooms[j][1] + 10 <= cur[0]) rooms.splice(j, 1);
        }
        rooms.push(cur);
        mx = Math.max(rooms.length, mx);
    }
    
    return mx;
}