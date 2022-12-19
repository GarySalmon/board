import requsest from "@/utils/request"

const url = "users"

export function users(data) {
  return requsest({
    url: url,
    method: "get",
    data,
  })
}

export function usersCreate(data) {
  return requsest.post(url, data)
}

export function usersEdit(data) {
  return requsest.patch(url, data)
}

export function usersDel(data) {
  return requsest.delete(url, data)
}

export function login(data) {
  return requsest({
    url: "/login",
    method: "post",
    data,
  })
}
