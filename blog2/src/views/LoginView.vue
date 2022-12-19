<template>
  <!-- login form use tailwind css -->
  <div class="flex flex-col items-center justify-center h-screen">
    <div class="w-1/3">
      <h1 class="text-3xl font-bold text-center">Login</h1>
      <form class="mt-8">
        <div class="flex flex-col mb-4">
          <label class="mb-2 font-bold text-lg text-gray-700" for="username">Username</label>
          <input class="border py-2 px-3 text-gray-700" type="text" name="username" id="username" placeholder="Username" v-model="form.username" />
        </div>
        <div class="flex flex-col mb-4">
          <label class="mb-2 font-bold text-lg text-gray-700" for="password">Password</label>
          <input class="border py-2 px-3 text-gray-700" type="password" name="password" id="password" placeholder="Password" v-model="form.password" />
        </div>
        <div class="flex flex-col mb-4">
          <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" type="button" @click="submit()">Login</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { reactive } from "vue"
import { login } from "@/api/user"
// import { useToast } from "vue-toastification"
import { useRouter } from "vue-router"
export default {
  setup() {
    const form = reactive({
      username: "",
      password: "",
    })
    const router = useRouter()
    const submit = () => {
      try {
        const res = login(form)
        console.log(res)
        // useToast.success(res.data.message)
        router.push("/blog")
      } catch (error) {
        // useToast.error(error)
      }
    }
    return {
      form,
      submit,
    }
  },
}
</script>

<style></style>
