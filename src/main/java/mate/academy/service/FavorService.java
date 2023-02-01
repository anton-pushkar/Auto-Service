package mate.academy.service;

import mate.academy.model.Favor;

public interface FavorService {
    Favor create(Favor favor);

    Favor update(Favor favor);

    Favor getById(Long id);

    Favor changeStatus(Favor favor);
}
