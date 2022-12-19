<template>
  <!-- Main modal vue use showModal Contral -->
  <div v-if="Modal" class="fixed z-10 inset-0 overflow-y-auto">
    <div class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
      <div class="fixed inset-0 transition-opacity" aria-hidden="true">
        <div class="absolute inset-0 bg-gray-500 opacity-75"></div>
      </div>
      <!-- This element is to trick the browser into centering the modal contents. -->
      <span class="hidden sm:inline-block sm:align-middle sm:h-screen" aria-hidden="true">&#8203;</span>
      <!-- Modal -->
      <div class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
        <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
          <div class="sm:flex sm:items-start">
            <div class="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
              <h3 class="text-lg leading-6 font-medium text-gray-900" id="modal-headline">{{ isCreate ? "新增" : "修改" }}文章</h3>
              <div class="mt-2">
                <form class="w-full max-w-sm">
                  <div class="flex flex-wrap">
                    <div class="w-full px-3">
                      <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-title"> 標題 </label>
                      <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="grid-title" type="text" placeholder="標題" v-model="tmpBlog.title" />
                      <p class="text-gray-600 text-xs italic">請輸入標題</p>
                    </div>
                  </div>
                  <div class="w-full px-3">
                    <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-content"> 內容 </label>
                    <textarea class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="grid-content" type="text" placeholder="內容" v-model="tmpBlog.content"></textarea>
                    <p class="text-gray-600 text-xs italic">請輸入內容</p>
                  </div>
                  <div class="w-full px-3">
                    <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="grid-deadline"> 截止日期 </label>
                    <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white" id="grid-deadline" type="date" placeholder="截止日期" v-model="tmpBlog.deadline" :min="now" />
                    <p class="text-gray-600 text-xs italic">請輸入截止日期</p>
                  </div>
                  <!-- submit and cancel -->
                  <div class="flex items-center justify-between mt-10">
                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button" @click="submitBlog" v-if="isCreate">新增</button>
                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button" @click="updateBlog" v-else>修改</button>
                    <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button" @click="Modal = false">取消</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- use table show blog list  -->
  <div class="flex flex-col items-center h-screen mt-20">
    <div>
      <h1 class="text-3xl font-bold text-center mb-10">Blog</h1>
      <!-- create blog btn -->
      <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" @click="createBlog">新增文章</button>
    </div>
    <table class="table-auto">
      <thead>
        <tr>
          <th class="px-4 py-2">標題</th>
          <th class="px-4 py-2">發布日期</th>
          <th class="px-4 py-2">截止日期</th>
          <th class="px-4 py-2">修改</th>
          <th class="px-4 py-2">刪除</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="blog in blogs.data" :key="blog.id">
          <td class="border px-4 py-2">{{ blog.title }}</td>
          <td class="border px-4 py-2">{{ blog.publish }}</td>
          <td class="border px-4 py-2">{{ blog.deadline }}</td>
          <td class="border px-4 py-2">
            <button @click="showModal(blog)">修改</button>
          </td>
          <td class="border px-4 py-2">
            <button @click="deleteBlog(blog.message_id)">刪除</button>
          </td>
        </tr>
      </tbody>
      <!-- change page show max 5 page and < >  -->
      <tfoot>
        <tr>
          <td class="border px-4 py-2" colspan="5">
            <div class="flex justify-center">
              <button class="bg-blue-500 mx-2 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button" @click="getBlogsByPage(currentPage - 1)">
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 19l-7-7m0 0l7-7m-7 7h18"></path></svg>
              </button>
              <span class="mx-2">第 {{ currentPage }} / {{ totalPage }} 頁 </span>
              <button class="bg-blue-500 mx-2 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="button" @click="getBlogsByPage(currentPage + 1)">
                <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 5l7 7m0 0l-7 7m7-7H3"></path></svg>
              </button>
            </div>
          </td>
        </tr>
      </tfoot>
    </table>
  </div>
</template>

<script>
import { reactive, ref, onMounted } from "vue"
import { toast } from "vue3-toastify"
import { blog, blogCreate, blogEdit, blogDel, blogPages } from "@/api/blog"
export default {
  setup() {
    const totalPage = ref(3)
    const currentPage = ref(1)
    const isCreate = ref(false)
    const user_id = ref(100)
    const date = new Date()
    let now = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
    const tmpBlog = reactive({ id: "", title: "", content: "", deadline: "" })
    const Modal = ref(false)
    const blogs = reactive({ data: [] })
    const deleteBlog = async (id) => {
      try {
        await blogDel(id)
        await getBlogsByPage(1)
        toast("刪除成功", {
          autoClose: 1000,
        })
      } catch (error) {
        console.log(error)
      }
    }
    const showModal = (blog) => {
      Modal.value = true
      isCreate.value = false
      tmpBlog.message_id = blog.message_id
      tmpBlog.id = blog.id
      tmpBlog.title = blog.title
      tmpBlog.content = blog.content
      tmpBlog.deadline = blog.deadline.split(" ")[0]
    }

    const updateBlog = async () => {
      sumbitProcee()
      try {
        await blogEdit(tmpBlog, tmpBlog.message_id)
        toast("修改成功", {
          autoClose: 1000,
        })
        Modal.value = false
        await getBlogsByPage(1)
      } catch (error) {
        console.log(error)
      }
    }
    const createBlog = () => {
      const date = new Date()
      now = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate()
      Modal.value = true
      isCreate.value = true
      console.log("createBlog")
      tmpBlog.id = ""
      tmpBlog.title = ""
      tmpBlog.content = ""
      // tmpBlog.message_id =
      tmpBlog.deadline = now
      tmpBlog.user_id = user_id.value
    }

    const sumbitProcee = () => {
      delete tmpBlog.id
      tmpBlog.publish = '"' + now + ' 00:00:00"'
      tmpBlog.deadline = '"' + tmpBlog.deadline + ' 00:00:00"'
    }

    const submitBlog = async () => {
      sumbitProcee()
      try {
        await blogCreate(tmpBlog)
        Modal.value = false
        await getBlogsByPage(currentPage.value)
        toast("新增成功", {
          autoClose: 1000,
        })
      } catch (error) {
        console.log(error)
      }
      console.log("submitBlog")
    }
    const getTotalPage = async (data) => {
      try {
        const res = await blogPages(data)
        totalPage.value = res.data
      } catch (error) {
        console.log(error)
      }
    }
    const getBlogsByPage = async (page) => {
      page = page < 1 ? 1 : page
      page = page > totalPage.value ? totalPage.value : page
      currentPage.value = page
      console.log("getBlogsByPage")
      let param = [page - 1, 5]
      try {
        const res = await blog(param)
        blogs.data = res.data
        blogs.data.forEach((el) => {
          // delete '"'
          el.deadline = el.deadline.slice(1, -1)
          el.publish = el.publish.slice(1, -1)
        })

        toast("獲取成功", {
          autoClose: 1000,
        })
        await getTotalPage(param)
      } catch (error) {
        console.log(error)
      }
    }

    onMounted(() => {
      getBlogsByPage(1)
    })

    return {
      now,
      Modal,
      isCreate,
      blogs,
      tmpBlog,
      totalPage,
      currentPage,
      createBlog,
      submitBlog,
      showModal,
      deleteBlog,
      updateBlog,
      getBlogsByPage,
    }
  },
}
</script>

<style></style>
