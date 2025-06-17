package web.elearning.mapper;

import web.elearning.dto.request.AccountRequest;
import web.elearning.dto.response.AccountResponse;
import web.elearning.model.Account;

public class AccountMapper {
    public static Account addRequestToEntity(AccountRequest request, String password) {
        Account account = new Account();
        account.setName(request.getName());
        account.setEmail(request.getEmail());
        account.setPassword(password);
        account.setPhoneNumber(request.getPhoneNumber());
        account.setRoles("USER");
        return account;
    }

    public static Account updateRequestToEntity(AccountRequest request, Long id) {
        Account account = new Account();
        account.setId(id);
        account.setName(request.getName());
        account.setEmail(request.getEmail());
        account.setPassword(request.getPassword());
        account.setPhoneNumber(request.getPhoneNumber());
        account.setRoles("USER");
        return account;
    }

    public static AccountResponse entityToResponse(Account entity) {
        AccountResponse account = new AccountResponse();
        account.setId(entity.getId());
        account.setName(entity.getName());
        account.setEmail(entity.getEmail());
        account.setLearnerResponse(LearnerMapper.entityToResponse(entity.getLearner()));
        account.setPhoneNumber(entity.getPhoneNumber());
        account.setRoles(entity.getRoles());
        return account;
    }
}
