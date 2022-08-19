import request from '@/utils/request';

export function getCategoryList(params) {
    return request({
        url: '/categories',
        method: 'get',
        params
    })
}

export function addCategory(params) {
    return request({
        url: '/categories',
        method: 'post',
        data: params
    })
}

export function modifyCategory(params) {
    return request({
        url: '/categories',
        method: 'put',
        data: params
    })
}

export function removeCategory(params) {
    return request({
        url: '/categories',
        method: 'delete',
        data: params
    })
}
