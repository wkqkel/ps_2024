function solution(name, yearning, photo) {
    const y_map = name.reduce((acc,cur,idx)=>{
        return {...acc, [cur]:yearning[idx]}
    },{})
    
    return photo.map(v => v.reduce((acc,cur)=> {
       return acc + (y_map[cur] || 0);
    },0))
}