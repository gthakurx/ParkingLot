package respositories;
/*
purpose of this class is to Have every Method allows to do CRUD operations on Gate DB .
 */

import models.Gate;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/*
* Have every method which allows to do CRUD operation on gate on DB
*
* */

/*
* We will use TreeMap to store the values so that can be used later
* why we are Using treeMap , as the Hashmap definatley do the operation in O(1)
* but for the range queries treemap will be better choice as it is sorted
* for example : find the Gate with ID from 0 to 10 the treemap will do in logn and hashmap will
* do in o(n)
*
* we can look more on this
* */
public class GateRepository {

    private Map<Long,Gate> gates=new TreeMap<>();

    public Optional<Gate> findGateById(Long gateId){
        if(gates.containsKey(gateId)){
            return Optional.of(gates.get(gateId));
        }
        return Optional.empty();
    }
}
//instead of returning NULL there are Optional object
//Optional<Gate>
//