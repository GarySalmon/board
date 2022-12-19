import requsest from "@/utils/request"

const url = "msgs"
export function blog(data) {
  return requsest({
    url: url + "/" + data[0] + "/" + data[1],
    method: "get",
  })
}

export function blogPages(data) {
  return requsest({
    url: url + "Pages" + "/" + data[0] + "/" + data[1],
    method: "get",
  })
}

export function blogCreate(data) {
  return requsest.post(url, data)
}

export function blogEdit(data, id) {
  return requsest.put(url + "/" + id, data)
}

export function blogDel(data) {
  return requsest.delete(url + "/" + data)
}
