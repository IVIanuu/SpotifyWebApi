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

import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.PREFIXES.MAX;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.PREFIXES.MIN;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.PREFIXES.TARGET;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.ACOUSTICNESS;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.DANCEABILITY;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.DURATION;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.ENERGY;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.INSTRUMENTALNESS;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.KEY;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.LIMIT;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.LIVENESS;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.LOUDNESS;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.MARKET;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.MODE;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.POPULARITY;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.SEED_ARTISTS;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.SEED_GENRES;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.SEED_TRACKS;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.SPEECHINESS;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.TEMPO;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.TIME_SIGNATURE;
import static com.ivianuu.spotifyextensions.RecommendationsBuilder.VALUES.QUERY_PARAMS.VALENCE;

/**
 * A class which makes building recommendation options a breeze
 */

public final class RecommendationsBuilder {

    private HashMap<String, String> seeds = new HashMap<>();
    private HashMap<String, Object> options = new HashMap<>();

    /**
     * @param preset default map
     */
    public RecommendationsBuilder preset(@NonNull HashMap<String, Object> preset) {
        options.putAll(preset);
        return this;
    }

    // SEEDS

    /**
     * Adds the artist to the seeds
     * @param artistId the artist id
     */
    public RecommendationsBuilder artistSeed(@NonNull String artistId) {
        return seed(artistId, VALUES.SEED_TYPES.ARTIST);
    }

    /**
     * Adds the artists to the seeds
     * @param artistIds the artist ids
     */
    public RecommendationsBuilder artistSeeds(@NonNull String... artistIds) {
        return artistSeeds(Arrays.asList(artistIds));
    }

    /**
     * Adds the artists to the seeds
     * @param artistIds the artist ids
     */
    public RecommendationsBuilder artistSeeds(@NonNull List<String> artistIds) {
        HashMap<String, String> seeds = new HashMap<>();
        for (String artistId : artistIds) {
            seeds.put(artistId, VALUES.SEED_TYPES.ARTIST);
        }
        return seeds(seeds);
    }

    /**
     * Adds the genre to the seeds
     * @param genreId the genre id
     */
    public RecommendationsBuilder genreSeed(@NonNull String genreId) {
        return seed(genreId, VALUES.SEED_TYPES.GENRE);
    }

    /**
     * Adds the genres to the seeds
     * @param genreIds the genre ids
     */
    public RecommendationsBuilder genreSeeds(@NonNull String... genreIds) {
        return genreSeeds(Arrays.asList(genreIds));
    }

    /**
     * Adds the genres to the seeds
     * @param genreIds the genre ids
     */
    public RecommendationsBuilder genreSeeds(@NonNull List<String> genreIds) {
        HashMap<String, String> seeds = new HashMap<>();
        for (String id : genreIds) {
            seeds.put(id, VALUES.SEED_TYPES.GENRE);
        }
        return seeds(seeds);
    }

    /**
     * Adds the track to the seeds
     * @param trackId the track id
     */
    public RecommendationsBuilder trackSeed(@NonNull String trackId) {
        return seed(trackId, VALUES.SEED_TYPES.TRACK);
    }

    /**
     * Adds the tracks to the seeds
     * @param trackIds the track ids
     */
    public RecommendationsBuilder trackSeeds(@NonNull String... trackIds) {
        return trackSeeds(Arrays.asList(trackIds));
    }

    /**
     * Adds the tracks to the seeds
     * @param trackIds the track ids
     */
    public RecommendationsBuilder trackSeeds(@NonNull List<String> trackIds) {
        HashMap<String, String> seeds = new HashMap<>();
        for (String id : trackIds) {
            seeds.put(id, VALUES.SEED_TYPES.TRACK);
        }
        return seeds(seeds);
    }

    /**
     * Adds a seed
     * @param type the type which should be one of artist, genre or track
     * @param id the id of the seed
     */
    public RecommendationsBuilder seed(@NonNull String type, @NonNull String id) {
        this.seeds.put(type, id);
        return this;
    }

    /**
     * Adds the seeds
     * @param seeds should be like type, id
     */
    public RecommendationsBuilder seeds(@NonNull HashMap<String, String> seeds) {
        this.seeds.putAll(seeds);
        return this;
    }

    // TUNABLE

    /**
     * A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high confidence the track is acoustic.
     * @param maxAcousticness the max acousticness
     */
    public RecommendationsBuilder maxAcousticness(@FloatRange(from = 0, to = 1.0f) float maxAcousticness) {
        options.put(MAX + ACOUSTICNESS, maxAcousticness);
        return this;
    }

    /**
     * A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high confidence the track is acoustic.
     * @param minAcousticness the min acousticness
     */
    public RecommendationsBuilder minAcousticness(@FloatRange(from = 0, to = 1.0f) float minAcousticness) {
        options.put(MIN + ACOUSTICNESS, minAcousticness);
        return this;
    }

    /**
     * A confidence measure from 0.0 to 1.0 of whether the track is acoustic. 1.0 represents high confidence the track is acoustic.
     * @param targetAcousticness the target acousticness
     */
    public RecommendationsBuilder targetAcousticness(@FloatRange(from = 0, to = 1.0f) float targetAcousticness) {
        options.put(TARGET + ACOUSTICNESS, targetAcousticness);
        return this;
    }

    /**
     * Danceability describes how suitable a track is for dancing based on a combination of musical elements including tempo, rhythm stability, beat strength, and overall regularity. A value of 0.0 is least danceable and 1.0 is most danceable.
     * @param maxDanceability the max danceability
     */
    public RecommendationsBuilder maxDanceability(@FloatRange(from = 0, to = 1.0f) float maxDanceability) {
        options.put(MAX + DANCEABILITY, maxDanceability);
        return this;
    }

    /**
     * Danceability describes how suitable a track is for dancing based on a combination of musical elements.
     * Including tempo, rhythm stability, beat strength, and overall regularity.
     * A value of 0.0 is least danceable and 1.0 is most danceable.
     * @param minDanceability the min danceability
     */
    public RecommendationsBuilder minDanceability(@FloatRange(from = 0, to = 1.0f) float minDanceability) {
        options.put(MIN + DANCEABILITY, minDanceability);
        return this;
    }

    /**
     * Danceability describes how suitable a track is for dancing based on a combination of musical elements.
     * Including tempo, rhythm stability, beat strength, and overall regularity.
     * A value of 0.0 is least danceable and 1.0 is most danceable.
     * @param targetDanceability the target danceability
     */
    public RecommendationsBuilder targetDanceability(@FloatRange(from = 0, to = 1.0f) float targetDanceability) {
        options.put(TARGET + DANCEABILITY, targetDanceability);
        return this;
    }

    /**
     * The duration of the track in milliseconds.
     * @param maxDuration the max duration
     */
    public RecommendationsBuilder maxDuration(int maxDuration) {
        options.put(MAX + DURATION, maxDuration);
        return this;
    }

    /**
     * The duration of the track in milliseconds.
     * @param minDuration the min duration
     */
    public RecommendationsBuilder minDuration(int minDuration) {
        options.put(MIN + DURATION, minDuration);
        return this;
    }

    /**
     * The duration of the track in milliseconds.
     * @param targetDuration the target duration
     */
    public RecommendationsBuilder targetDuration(int targetDuration) {
        options.put(TARGET + DURATION, targetDuration);
        return this;
    }

    /**
     * Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and activity.
     * Typically, energetic tracks feel fast, loud, and noisy.
     * For example, death metal has high energy, while a Bach prelude scores low on the scale.
     * Perceptual features contributing to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general entropy.
     * @param maxEnergy the max energy
     */
    public RecommendationsBuilder maxEnergy(@FloatRange(from = 0, to = 1.0f) float maxEnergy) {
        options.put(MAX + ENERGY, maxEnergy);
        return this;
    }

    /**
     * Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and activity.
     * Typically, energetic tracks feel fast, loud, and noisy.
     * For example, death metal has high energy, while a Bach prelude scores low on the scale.
     * Perceptual features contributing to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general entropy.
     * @param minEnergy the min energy
     */
    public RecommendationsBuilder minEnergy(@FloatRange(from = 0, to = 1.0f)float minEnergy) {
        options.put(MIN + ENERGY, minEnergy);
        return this;
    }

    /**
     * Energy is a measure from 0.0 to 1.0 and represents a perceptual measure of intensity and activity.
     * Typically, energetic tracks feel fast, loud, and noisy.
     * For example, death metal has high energy, while a Bach prelude scores low on the scale.
     * Perceptual features contributing to this attribute include dynamic range, perceived loudness, timbre, onset rate, and general entropy.
     * @param targetEnergy the target energy
     */
    public RecommendationsBuilder targetEnergy(@FloatRange(from = 0, to = 1.0f) float targetEnergy) {
        options.put(TARGET + ENERGY, targetEnergy);
        return this;
    }

    /**
     * Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context.
     * Rap or spoken word tracks are clearly "vocal".
     * The closer the instrumentalness value is to 1.0, the greater likelihood the track contains no vocal content.
     * Values above 0.5 are intended to represent instrumental tracks, but confidence is higher as the value approaches 1.0.
     * @param maxInstrumentalness the max instrumentalness
     */
    public RecommendationsBuilder maxInstrumentalness(@FloatRange(from = 0, to = 1.0f) float maxInstrumentalness) {
        options.put(MAX + INSTRUMENTALNESS, maxInstrumentalness);
        return this;
    }

    /**
     * Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context.
     * Rap or spoken word tracks are clearly "vocal".
     * The closer the instrumentalness value is to 1.0, the greater likelihood the track contains no vocal content.
     * Values above 0.5 are intended to represent instrumental tracks, but confidence is higher as the value approaches 1.0.
     * @param minInstrumentalness the min instrumentalness
     */
    public RecommendationsBuilder minInstrumentalness(@FloatRange(from = 0, to = 1.0f) float minInstrumentalness) {
        options.put(MIN + INSTRUMENTALNESS, minInstrumentalness);
        return this;
    }

    /**
     * Predicts whether a track contains no vocals. "Ooh" and "aah" sounds are treated as instrumental in this context.
     * Rap or spoken word tracks are clearly "vocal".
     * The closer the instrumentalness value is to 1.0, the greater likelihood the track contains no vocal content.
     * Values above 0.5 are intended to represent instrumental tracks, but confidence is higher as the value approaches 1.0.
     * @param targetInstrumentalness the target instrumentalness
     */
    public RecommendationsBuilder targetInstrumentalness(@FloatRange(from = 0, to = 1.0f) float targetInstrumentalness) {
        options.put(TARGET + INSTRUMENTALNESS, targetInstrumentalness);
        return this;
    }

    /**
     * The key the track is in. Integers map to pitches using standard Pitch Class notation. E.g. 0 = C, 1 = C♯/D♭, 2 = D, and so on.
     * @param key the key
     */
    public RecommendationsBuilder withKey(int key) {
        options.put(KEY, key);
        return this;
    }

    /**
     * Detects the presence of an audience in the recording.
     * Higher liveness values represent an increased probability that the track was performed live.
     * A value above 0.8 provides strong likelihood that the track is live.
     * @param maxLiveness the max liveness
     */
    public RecommendationsBuilder maxLiveness(@FloatRange(from = 0, to = 1.0f) float maxLiveness) {
        options.put(MAX + LIVENESS, maxLiveness);
        return this;
    }

    /**
     * Detects the presence of an audience in the recording.
     * Higher liveness values represent an increased probability that the track was performed live.
     * A value above 0.8 provides strong likelihood that the track is live.
     * @param minLiveness the min liveness
     */
    public RecommendationsBuilder minLiveness(@FloatRange(from = 0, to = 1.0f) float minLiveness) {
        options.put(MIN + LIVENESS, minLiveness);
        return this;
    }

    /**
     * Detects the presence of an audience in the recording.
     * Higher liveness values represent an increased probability that the track was performed live.
     * A value above 0.8 provides strong likelihood that the track is live.
     * @param targetLiveness the target liveness
     */
    public RecommendationsBuilder targetLiveness(@FloatRange(from = 0, to = 1.0f) float targetLiveness) {
        options.put(TARGET + LIVENESS, targetLiveness);
        return this;
    }

    /**
     * Detects the presence of an audience in the recording.
     * Higher liveness values represent an increased probability that the track was performed live.
     * A value above 0.8 provides strong likelihood that the track is live.
     * @param maxLoudness the max loudness
     */
    public RecommendationsBuilder maxLoudness(float maxLoudness) {
        options.put(MAX + LOUDNESS, maxLoudness);
        return this;
    }

    /**
     * Detects the presence of an audience in the recording.
     * Higher liveness values represent an increased probability that the track was performed live.
     * A value above 0.8 provides strong likelihood that the track is live.
     * @param minLoudness the min loudness
     */
    public RecommendationsBuilder minLoudness(float minLoudness) {
        options.put(MIN + LOUDNESS, minLoudness);
        return this;
    }

    /**
     * Detects the presence of an audience in the recording.
     * Higher liveness values represent an increased probability that the track was performed live.
     * A value above 0.8 provides strong likelihood that the track is live.
     * @param targetLoudness the target loudness
     */
    public RecommendationsBuilder targetLoudness(float targetLoudness) {
        options.put(TARGET + LOUDNESS, targetLoudness);
        return this;
    }

    /**
     * Mode indicates the modality (major or minor) of a track, the type of scale from which its melodic content is derived.
     * Major is represented by 1 and minor is 0.
     * @param mode the mode
     */
    public RecommendationsBuilder mode(int mode) {
        options.put(MODE, mode);
        return this;
    }

    /**
     * The popularity of the track. The value will be between 0 and 100, with 100 being the most popular.
     * The popularity is calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how recent those plays are.
     * @param maxPopularity the max popularity
     */
    public RecommendationsBuilder maxPopularity(@IntRange(from = 0, to = 100) int maxPopularity) {
        options.put(MAX + POPULARITY, maxPopularity);
        return this;
    }

    /**
     * The popularity of the track. The value will be between 0 and 100, with 100 being the most popular.
     * The popularity is calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how recent those plays are.
     * @param minPopularity the min popularity
     */
    public RecommendationsBuilder minPopularity(@IntRange(from = 0, to = 100) int minPopularity) {
        options.put(MIN + POPULARITY, minPopularity);
        return this;
    }

    /**
     * The popularity of the track. The value will be between 0 and 100, with 100 being the most popular.
     * The popularity is calculated by algorithm and is based, in the most part, on the total number of plays the track has had and how recent those plays are.
     * @param targetPopularity the target popularity
     */
    public RecommendationsBuilder targetPopularity(@IntRange(from = 0, to = 100) int targetPopularity) {
        options.put(TARGET + POPULARITY, targetPopularity);
        return this;
    }

    /**
     * Speechiness detects the presence of spoken words in a track.
     * The more exclusively speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the attribute value.
     * Values above 0.66 describe tracks that are probably made entirely of spoken words.
     * Values between 0.33 and 0.66 describe tracks that may contain both music and speech, either in sections or layered, including such cases as rap music.
     * Values below 0.33 most likely represent music and other non-speech-like tracks.
     * @param maxSpeechiness the max speechiness
     */
    public RecommendationsBuilder maxSpeechiness(@FloatRange(from = 0, to = 1.0f) float maxSpeechiness) {
        options.put(MAX + SPEECHINESS, maxSpeechiness);
        return this;
    }

    /**
     * Speechiness detects the presence of spoken words in a track.
     * The more exclusively speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the attribute value.
     * Values above 0.66 describe tracks that are probably made entirely of spoken words.
     * Values between 0.33 and 0.66 describe tracks that may contain both music and speech, either in sections or layered, including such cases as rap music.
     * Values below 0.33 most likely represent music and other non-speech-like tracks.
     * @param minSpeechiness the min speechiness
     */
    public RecommendationsBuilder minSpeechiness(@FloatRange(from = 0, to = 1.0f) float minSpeechiness) {
        options.put(MIN + SPEECHINESS, minSpeechiness);
        return this;
    }

    /**
     * Speechiness detects the presence of spoken words in a track.
     * The more exclusively speech-like the recording (e.g. talk show, audio book, poetry), the closer to 1.0 the attribute value.
     * Values above 0.66 describe tracks that are probably made entirely of spoken words.
     * Values between 0.33 and 0.66 describe tracks that may contain both music and speech, either in sections or layered, including such cases as rap music.
     * Values below 0.33 most likely represent music and other non-speech-like tracks.
     * @param targetSpeechiness the target speechiness
     */
    public RecommendationsBuilder targetSpeechiness(@FloatRange(from = 0, to = 1.0f) float targetSpeechiness) {
        options.put(TARGET + SPEECHINESS, targetSpeechiness);
        return this;
    }

    /**
     * The overall estimated tempo of a track in beats per minute (BPM).
     * In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.
     * @param maxTempo the max tempo
     */
    public RecommendationsBuilder maxTempo(float maxTempo) {
        options.put(MAX + TEMPO, maxTempo);
        return this;
    }

    /**
     * The overall estimated tempo of a track in beats per minute (BPM).
     * In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.
     * @param minTempo the min tempo
     */
    public RecommendationsBuilder minTempo(float minTempo) {
        options.put(MIN + TEMPO, minTempo);
        return this;
    }

    /**
     * The overall estimated tempo of a track in beats per minute (BPM).
     * In musical terminology, tempo is the speed or pace of a given piece and derives directly from the average beat duration.
     * @param targetTempo the target tempo
     */
    public RecommendationsBuilder targetTempo(float targetTempo) {
        options.put(TARGET + TEMPO, targetTempo);
        return this;
    }

    /**
     * An estimated overall time signature of a track.
     * The time signature (meter) is a notational convention to specify how many beats are in each bar (or measure).
     * @param timeSignature the time signature
     */
    public RecommendationsBuilder timeSignature(int timeSignature) {
        options.put(TIME_SIGNATURE, timeSignature);
        return this;
    }

    /**
     * A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track.
     * Tracks with high valence sound more positive (e.g. happy, cheerful, euphoric)
     * while tracks with low valence sound more negative (e.g. sad, depressed, angry).
     * @param maxValence the max valence
     */
    public RecommendationsBuilder maxValence(@FloatRange(from = 0, to = 1.0f) float maxValence) {
        options.put(MAX + VALENCE, maxValence);
        return this;
    }

    /**
     * A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track.
     * Tracks with high valence sound more positive (e.g. happy, cheerful, euphoric)
     * while tracks with low valence sound more negative (e.g. sad, depressed, angry).
     * @param minValence the min valence
     */
    public RecommendationsBuilder minValence(@FloatRange(from = 0, to = 1.0f) float minValence) {
        options.put(MIN + VALENCE, minValence);
        return this;
    }

    /**
     * A measure from 0.0 to 1.0 describing the musical positiveness conveyed by a track.
     * Tracks with high valence sound more positive (e.g. happy, cheerful, euphoric)
     * while tracks with low valence sound more negative (e.g. sad, depressed, angry).
     * @param targetValence the target valence
     */
    public RecommendationsBuilder targetValence(@FloatRange(from = 0, to = 1.0f) float targetValence) {
        options.put(TARGET + VALENCE, targetValence);
        return this;
    }

    // MISC

    /**
     * The target size of recommended tracks
     * @param limit the limit
     */
    public RecommendationsBuilder limit(@IntRange(from = 1, to = 100) int limit) {
        options.put(LIMIT, limit);
        return this;
    }

    /**
     * For track relinking
     * @param market the market
     */
    public RecommendationsBuilder market(@NonNull String market) {
        options.put(MARKET, market);
        return this;
    }

    /**
     * @return the map which you can pass in the get recommendations method in the spotify service
     */
    public HashMap<String, Object> build() {
        if (seeds.isEmpty()) {
            throw new IllegalStateException("you have to add atleast one seed");
        } else if (seeds.size() > VALUES.MAX_SEEDS) {
            throw new IllegalStateException("you cannot add more than 5 seeds");
        }

        StringBuilder seedArtistsBuilder = new StringBuilder();
        StringBuilder seedTracksBuilder = new StringBuilder();
        StringBuilder seedGenresBuilder = new StringBuilder();

        for (String id : seeds.keySet()) {
            switch (seeds.get(id)) {
                case VALUES.SEED_TYPES.ARTIST:
                    seedArtistsBuilder.append(seedArtistsBuilder.length() == 0 ? id : "," + id);
                    break;
                case VALUES.SEED_TYPES.TRACK:
                    seedTracksBuilder.append(seedTracksBuilder.length() == 0 ? id : "," + id);
                    break;
                case VALUES.SEED_TYPES.GENRE:
                    seedGenresBuilder.append(seedGenresBuilder.length() == 0 ? id : "," + id);
                    break;
            }
        }

        if (seedArtistsBuilder.length() != 0) {
            options.put(SEED_ARTISTS, seedArtistsBuilder.toString());
        }
        if (seedTracksBuilder.length() != 0) {
            options.put(SEED_TRACKS, seedTracksBuilder.toString());
        }
        if (seedGenresBuilder.length() != 0) {
            options.put(SEED_GENRES, seedGenresBuilder.toString());
        }

        return options;
    }

    public interface VALUES {

        int MAX_SEEDS = 4;

        interface SEED_TYPES {
            String ARTIST = "artist";
            String TRACK = "track";
            String GENRE = "genre";
        }

        interface PREFIXES {
            String MAX = "max_";
            String MIN = "min_";
            String TARGET = "target_";
        }

        interface QUERY_PARAMS {
            String SEED_ARTISTS = "seed_artists";
            String SEED_GENRES = "seed_genres";
            String SEED_TRACKS = "seed_tracks";

            String LIMIT = "limit";
            String MARKET = "market";

            String ACOUSTICNESS = "acousticness";
            String DANCEABILITY = "danceability";
            String DURATION = "duration_ms";
            String ENERGY = "energy";
            String INSTRUMENTALNESS = "instrumentalness";
            String KEY = "key";
            String LIVENESS = "liveness";
            String LOUDNESS = "loudness";
            String MODE = "mode";
            String POPULARITY = "popularity";
            String SPEECHINESS = "speechiness";
            String TEMPO = "tempo";
            String TIME_SIGNATURE = "time_signature";
            String VALENCE = "valence";
        }

    }

}