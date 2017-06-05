/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ivianuu.spotifyextensions;

import android.support.annotation.NonNull;

import static com.ivianuu.spotifyextensions.UriHelper.Uri.ALBUM_URI;

/**
 * Easy manipulate spotify uri's and url's
 */

public final class UriHelper {

    public interface Uri {
        String ALBUM_URI = "spotify:album:";
        String ARTIST_URI = "spotify:artist:";
        String PLAYLIST_URI1 = "spotify:user:";
        String PLAYLIST_URI2 = ":playlist:";
        String TRACK_URI = "spotify:track:";
    }

    public interface Url {
        String ALBUM_URL = "https://open.spotify.com/album/";
        String ARTIST_URL = "https://open.spotify.com/artist/";
        String PLAYLIST_URL1 = "https://open.spotify.com/user/";
        String PLAYLIST_URL2 = "/playlist/";
        String TRACK_URL = "https://open.spotify.com/track/";
    }

    // GET

    public static String getAlbumUri(@NonNull String albumId) {
        return ALBUM_URI + albumId;
    }

    // EXTRACT
}
