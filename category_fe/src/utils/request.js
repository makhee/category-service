import axios from 'axios'
import { Message } from 'element-ui'

const service = axios.create({
	baseURL: 'http://localhost:8080', // url = base url + request url
	timeout: 5000, // request timeout
})

service.interceptors.request.use(
	config => {
		return config
	},
	error => {
		return Promise.reject(error)
	},
)

service.interceptors.response.use(
	response => {
		const res = response.data
		const httpStatusCode = response.status

		// http status code 200 정상 이외는 오류
		if (httpStatusCode !== 200) {
			Message({
				message: res.message || 'Error',
				type: 'error',
				duration: 5 * 1000,
			})
			return Promise.reject(new Error(res.message || 'Error'))
		} else {
			return res
		}
	},
	error => {
		Message({
			message: error.message,
			type: 'error',
			duration: 5 * 1000,
		})
		return Promise.reject(error.response.data)
	},
)

export default service
