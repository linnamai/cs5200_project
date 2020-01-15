
class util {
    bindMethods(methods, obj) {
        methods.forEach(func => {
            if(typeof func === 'function') {
                obj[func] = obj[func].bind(this);
            }
        })
    }
}

export default util;
