package ProjectIsa.bioskop.service;

import ProjectIsa.bioskop.domain.User;

public interface LoginServiceInterface {

	User validation(String username, String password);
}
