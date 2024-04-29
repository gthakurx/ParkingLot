package respositories;
/*
purpose of this class is to Have every Method allows to do CRUD operations on Gate DB .
 */

import models.Gate;

import java.util.Optional;

/*
* Have every method which allows to do CRUD operation on gate on DB
*
* */
public class GateRepository {

    public Optional<Gate> findGateById(Long gateId){
        return Optional.empty();
    }
}
//instead of returning NULL there are Optional object
//Optional<Gate>
//