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

package com.ivianuu.spotifyextensions.auth;

/**
 * All spotify scopes
 */

public interface Scopes {

    String PLAYLIST_READ_PRIVATE = "playlist-read-private";
    String PLAYLIST_READ_COLLABORATIVE = "playlist-read-collaborative";
    String PLAYLIST_MODIFY_PUBLIC = "playlist-modify-public";
    String PLAYLIST_MODIFY_PRIVATE = "playlist-modify-private";
    String STREAMING = "streaming";
    String USER_FOLLOW_MODIFY = "user-follow-modify";
    String USER_FOLLOW_READ = "user-follow-read";
    String USER_LIBRARY_MODIFY = "user-library-modify";
    String USER_LIBRARY_READ = "user-library-read";
    String USER_READ_BIRTHDATE = "user-read-birthdate";
    String USER_READ_EMAIL = "user-read-email";
    String USER_READ_PRIVATE = "user-read-private";
    String USER_READ_RECENTLY_PLAYED = "user-read-recently-played";
    String USER_TOP_READ = "user-top-read";
    String USER_MODIFY_PLAYBACK_STATE = "user-modify-playback-state";
    String USER_READ_PLAYBACK_STATE = "user-read-playback-state";

}