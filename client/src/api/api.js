function get_port() {
  return (!process.env.dev) ?
    location.port :
    process.env.dev
}

export async function request(body, type, port, host){
  if(!port) port=get_port();
  if(!host) host=location.hostname;
  return fetch('http://' + host + ":" + port + '/' + type, {
    method:"POST",
    body: JSON.stringify(body)
  }).then(response => {return response.json()}).catch(err => {console.error(err)});
}

export async function get(type, port=get_port(), host=location.hostname) {
  return fetch('http://' + host + ":" + port + '/' + type, {
    method:"GET"
  }).then(response => {return response.json()}).catch(err => {console.error(err)});
}
