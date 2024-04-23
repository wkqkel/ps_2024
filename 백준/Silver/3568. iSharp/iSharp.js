const fs = require('fs');
const input = fs.readFileSync('dev/stdin').toString().trim().split('\n')[0].split(";")[0];

const vars = [];
const pres = [];
const sufs = [];

const splited = input.split(", ");

for(let i = 0; i < splited.length; i++){
  let _pre = pres[i-1] || "";
  let _suf = "";
  let _var = "";
  let cur = splited[i].split(" ");

  if(cur.length == 2){
     _pre += cur[0];
  } 

  if(cur.length == 2){
    cur = cur[1];
  } else {
    cur = cur[0];
  }
  
  let idx = -1;
  for(let i = 0; i < cur.length; i++){
    if(!'abcdefghijklmnopqrstuvwxyz'.includes(cur[i].toLowerCase())){
      idx = i;
      break;
    }
  }
  
  if(idx != -1){
    _var = cur.slice(0, idx);
    _suf = cur.slice(idx);
  } else {
    _var = cur;
  }

  _suf = [..._suf.replaceAll('[]','3')].reverse().join('').replaceAll("3","[]");

  pres.push(_pre);
  vars.push(_var);
  sufs.push(_suf);
}

for(let i = 0; i < pres.length; i++){
  console.log(pres[i] + sufs[i] +" "+ vars[i] + ";");
}

