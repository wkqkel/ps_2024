const MAP = ['C#', 'D#', "F#", 'G#', "A#",'B#']
function solution(m, musicinfos) {
    MAP.forEach((s,i) => m = m.replaceAll(s, i));
    
    const res = musicinfos
    .map((info)=>{
        let [st,et,title,v] = info.split(',')
        MAP.forEach((s,i) => v = v.replaceAll(s, i));
        const s = +st.split(':')[0] * 60 + +st.split(':')[1];
        const e = +et.split(':')[0] * 60 + +et.split(':')[1];
        const len = e - s;
        
        const played = v.repeat(len).slice(0, len);
        return {s,title, played}
    })
    .filter(v=>v.played.includes(m))
    .sort((a,b)=> {
        return b.played.length - a.played.length;
    })
  
    return res[0]?.title || "(None)";
}