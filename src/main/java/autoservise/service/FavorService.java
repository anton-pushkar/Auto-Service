package autoservise.service;

import autoservise.model.Favor;

public interface FavorService {
    Favor create(Favor favor);

    Favor update(Favor favor);

    Favor getById(Long id);

    Favor changeStatus(Favor favor, String status);
}
