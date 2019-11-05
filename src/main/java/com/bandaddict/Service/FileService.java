package com.bandaddict.Service;

import com.bandaddict.Entity.User;
import com.bandaddict.Response.UploadResponse;

public interface FileService {

    /**
     * Create path for the upload
     *
     * @param user current user
     * @param uploadResponse file path
     * @param type type
     * @return Upload response with path
     */
    UploadResponse setPath(final User user, final UploadResponse uploadResponse, final String type);

}
