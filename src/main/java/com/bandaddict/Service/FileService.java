package com.bandaddict.Service;

import com.bandaddict.Entity.User;

public interface FileService {

    /**
     * Get saved img path
     *
     * @param user current user
     * @param imgName img name
     * @param type type of the img
     * @return string path
     */
    String getPath(final User user, final String imgName, final String type);
}
