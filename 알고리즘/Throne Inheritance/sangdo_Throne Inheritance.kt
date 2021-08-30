class ThroneInheritance(var kingName: String) {
    var hashMap = HashMap<String, ArrayList<String>>()
    var alive = HashSet<String>()
    init{
        alive.add(kingName)
        hashMap[kingName] = ArrayList<String>()
    }
    fun birth(parentName: String, childName: String) {
        hashMap[parentName]!!.add(childName)
        hashMap[childName] = ArrayList<String>()
        alive.add(childName)
    }

    fun death(name: String) {
        alive.remove(name)
    }
    
    fun getOrder(name: String, res: ArrayList<String>): List<String> {
        if(name in alive) res.add(name)
        for(n in hashMap[name]!!) {
            getOrder(n, res)
        }
        return res
    }

    fun getInheritanceOrder(): List<String> {
        return getOrder(kingName,ArrayList<String>())
    }

}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * var obj = ThroneInheritance(kingName)
 * obj.birth(parentName,childName)
 * obj.death(name)
 * var param_3 = obj.getInheritanceOrder()
 */
