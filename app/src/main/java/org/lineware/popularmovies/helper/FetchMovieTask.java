package org.lineware.popularmovies.helper;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

/**
 * Created by jmsykes15 on 6/4/16.
 */
public class FetchMovieTask extends AsyncTask<String, Void, Void>{

    private final String TAG = FetchMovieTask.class.getSimpleName();

    private final Context mContext;

    public FetchMovieTask(Context mContext) {
        this.mContext = mContext;
    }

    private void getMovieDataFromJson(String movieJsonStr, String sortCriteria)throws JSONException{

        final String OWM_RESULT =  "results";
        final String OWM_TITLE = "original_title";
        final String OWM_ID = "id";
        final String OWM_RELEASE = "release_date";
        final String OWM_POSTER =  "poster_path";
        final String OWM_BACKDROP = "backdrop_path";
        final String OWM_RATING = "vote_average";
        final String OWM_PLOT = "overview";

        try{
            JSONObject movieJson = new JSONObject(movieJsonStr);
            JSONArray movieResults = movieJson.getJSONArray(OWM_RESULT);


            Vector<ContentValues> cVVector = new Vector<>(movieResults.length());

            for(int i = 0; i < movieResults.length(); i++){

                JSONObject movieResultsObject = movieResults.getJSONObject(i);

                String titleObject = movieResultsObject.getString(OWM_TITLE);
                int idObject = movieResultsObject.getInt(OWM_ID);
                String releaseObject = movieResultsObject.getString(OWM_RELEASE);
                String posterpathObject = movieResultsObject.getString(OWM_POSTER);
                String backdroppathObject = movieResultsObject.getString(OWM_BACKDROP);
                double voteaverageObject = movieResultsObject.getDouble(OWM_RATING);
                String overviewObject = movieResultsObject.getString(OWM_PLOT);

                ContentValues movieValues = new ContentValues();

                movieValues.put(MovieContract.MovieEntry.COLUMN_TITLE,titleObject);
                movieValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, idObject);
                movieValues.put(MovieContract.MovieEntry.COLUMN_RELEASE, releaseObject);
                movieValues.put(MovieContract.MovieEntry.COLUMN_POSTER, posterpathObject);
                movieValues.put(MovieContract.MovieEntry.COLUMN_BANNER, backdroppathObject);
                movieValues.put(MovieContract.MovieEntry.COLUMN_RATING, voteaverageObject);
                movieValues.put(MovieContract.MovieEntry.COLUMN_PLOT, overviewObject);

                cVVector.add(movieValues);
            }

            int inserted = 0;

            if(cVVector.size() > 0){
                ContentValues[] cvArray = new ContentValues[cVVector.size()];
                cVVector.toArray(cvArray);
                inserted = mContext.getContentResolver().bulkInsert(MovieContract.MovieEntry.CONTENT_URI, cvArray);
            }

            Log.d(TAG, "getMovieDataFromJson: FetchMovieTask Complete. " + inserted + " Inserted");
        }catch (JSONException e ){
            Log.e(TAG, "getMovieDataFromJson: ", e);
            e.printStackTrace();
        }

    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            getMovieDataFromJson(tempData(), "popularity");
        } catch (JSONException e) {
            Log.e(TAG, "doInBackground: ", e);
            e.printStackTrace();
        }
        return null;
    }

    private String tempData(){
        String temp = "{\n" +
                "   \"page\":1,\n" +
                "   \"results\":[\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/5N20rQURev5CNDcMjHVUZhpoCNC.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"Following the events of Age of Ultron, the collective governments of the world pass an act designed to regulate all superhuman activity. This polarizes opinion amongst the Avengers, causing two factions to side with Iron Man or Captain America, which causes an epic battle between former allies.\",\n" +
                "         \"release_date\":\"2016-04-27\",\n" +
                "         \"genre_ids\":[\n" +
                "            28,\n" +
                "            878,\n" +
                "            53\n" +
                "         ],\n" +
                "         \"id\":271110,\n" +
                "         \"original_title\":\"Captain America: Civil War\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Captain America: Civil War\",\n" +
                "         \"backdrop_path\":\"\\/m5O3SZvQ6EgD5XXXLPIP1wLppeW.jpg\",\n" +
                "         \"popularity\":83.293604,\n" +
                "         \"vote_count\":502,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":6.84\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/k1QUCjNAkfRpWfm1dVJGUmVHzGv.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"Based upon Marvel Comics’ most unconventional anti-hero, DEADPOOL tells the origin story of former Special Forces operative turned mercenary Wade Wilson, who after being subjected to a rogue experiment that leaves him with accelerated healing powers, adopts the alter ego Deadpool. Armed with his new abilities and a dark, twisted sense of humor, Deadpool hunts down the man who nearly destroyed his life.\",\n" +
                "         \"release_date\":\"2016-02-09\",\n" +
                "         \"genre_ids\":[\n" +
                "            10749,\n" +
                "            28,\n" +
                "            12,\n" +
                "            35\n" +
                "         ],\n" +
                "         \"id\":293660,\n" +
                "         \"original_title\":\"Deadpool\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Deadpool\",\n" +
                "         \"backdrop_path\":\"\\/n1y094tVDFATSzkTnFxoGZ1qNsG.jpg\",\n" +
                "         \"popularity\":40.421604,\n" +
                "         \"vote_count\":3005,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":7.22\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/6bCplVkhowCjTHXWv49UjRPn0eK.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"Fearing the actions of a god-like Super Hero left unchecked, Gotham City’s own formidable, forceful vigilante takes on Metropolis’s most revered, modern-day savior, while the world wrestles with what sort of hero it really needs. And with Batman and Superman at war with one another, a new threat quickly arises, putting mankind in greater danger than it’s ever known before.\",\n" +
                "         \"release_date\":\"2016-03-23\",\n" +
                "         \"genre_ids\":[\n" +
                "            28,\n" +
                "            12,\n" +
                "            14\n" +
                "         ],\n" +
                "         \"id\":209112,\n" +
                "         \"original_title\":\"Batman v Superman: Dawn of Justice\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Batman v Superman: Dawn of Justice\",\n" +
                "         \"backdrop_path\":\"\\/vsjBeMPZtyB7yNsYY56XYxifaQZ.jpg\",\n" +
                "         \"popularity\":30.560535,\n" +
                "         \"vote_count\":1847,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":5.69\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/vOipe2myi26UDwP978hsYOrnUWC.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"An orphan boy is raised in the Jungle with the help of a pack of wolves, a bear and a black panther.\",\n" +
                "         \"release_date\":\"2016-04-07\",\n" +
                "         \"genre_ids\":[\n" +
                "            12,\n" +
                "            18,\n" +
                "            14\n" +
                "         ],\n" +
                "         \"id\":278927,\n" +
                "         \"original_title\":\"The Jungle Book\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"The Jungle Book\",\n" +
                "         \"backdrop_path\":\"\\/eIOTsGg9FCVrBc4r2nXaV61JF4F.jpg\",\n" +
                "         \"popularity\":25.526133,\n" +
                "         \"vote_count\":468,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":6.35\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/dlIPGXPxXQTp9kFrRzn0RsfUelx.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"Predominantly set during World War II, Steve Rogers is a sickly man from Brooklyn who's transformed into super-soldier Captain America to aid in the war effort. Rogers must stop the Red Skull – Adolf Hitler's ruthless head of weaponry, and the leader of an organization that intends to use a mysterious device of untold powers for world domination.\",\n" +
                "         \"release_date\":\"2011-07-22\",\n" +
                "         \"genre_ids\":[\n" +
                "            878,\n" +
                "            28,\n" +
                "            12\n" +
                "         ],\n" +
                "         \"id\":1771,\n" +
                "         \"original_title\":\"Captain America: The First Avenger\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Captain America: The First Avenger\",\n" +
                "         \"backdrop_path\":\"\\/pmZtj1FKvQqISS6iQbkiLg5TAsr.jpg\",\n" +
                "         \"popularity\":25.26668,\n" +
                "         \"vote_count\":4423,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":6.43\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/weUSwMdQIa3NaXVzwUoIIcAi85d.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"Thirty years after defeating the Galactic Empire, Han Solo and his allies face a new threat from the evil Kylo Ren and his army of Stormtroopers.\",\n" +
                "         \"release_date\":\"2015-12-15\",\n" +
                "         \"genre_ids\":[\n" +
                "            28,\n" +
                "            12,\n" +
                "            878,\n" +
                "            14\n" +
                "         ],\n" +
                "         \"id\":140607,\n" +
                "         \"original_title\":\"Star Wars: The Force Awakens\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Star Wars: The Force Awakens\",\n" +
                "         \"backdrop_path\":\"\\/c2Ax8Rox5g6CneChwy1gmu4UbSb.jpg\",\n" +
                "         \"popularity\":24.697581,\n" +
                "         \"vote_count\":3967,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":7.63\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/5TQ6YDmymBpnF005OyoB7ohZps9.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"After the cataclysmic events in New York with The Avengers, Steve Rogers, aka Captain America is living quietly in Washington, D.C. and trying to adjust to the modern world. But when a S.H.I.E.L.D. colleague comes under attack, Steve becomes embroiled in a web of intrigue that threatens to put the world at risk. Joining forces with the Black Widow, Captain America struggles to expose the ever-widening conspiracy while fighting off professional assassins sent to silence him at every turn. When the full scope of the villainous plot is revealed, Captain America and the Black Widow enlist the help of a new ally, the Falcon. However, they soon find themselves up against an unexpected and formidable enemy—the Winter Soldier.\",\n" +
                "         \"release_date\":\"2014-03-20\",\n" +
                "         \"genre_ids\":[\n" +
                "            28,\n" +
                "            12,\n" +
                "            878\n" +
                "         ],\n" +
                "         \"id\":100402,\n" +
                "         \"original_title\":\"Captain America: The Winter Soldier\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Captain America: The Winter Soldier\",\n" +
                "         \"backdrop_path\":\"\\/4qfXT9BtxeFuamR4F49m2mpKQI1.jpg\",\n" +
                "         \"popularity\":23.384384,\n" +
                "         \"vote_count\":3168,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":7.63\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"In the 1820s, a frontiersman, Hugh Glass, sets out on a path of vengeance against those who left him for dead after a bear mauling.\",\n" +
                "         \"release_date\":\"2015-12-25\",\n" +
                "         \"genre_ids\":[\n" +
                "            37,\n" +
                "            18,\n" +
                "            12,\n" +
                "            53\n" +
                "         ],\n" +
                "         \"id\":281957,\n" +
                "         \"original_title\":\"The Revenant\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"The Revenant\",\n" +
                "         \"backdrop_path\":\"\\/kiWvoV78Cc3fUwkOHKzyBgVdrDD.jpg\",\n" +
                "         \"popularity\":22.751592,\n" +
                "         \"vote_count\":2551,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":7.17\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/gj282Pniaa78ZJfbaixyLXnXEDI.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"Katniss Everdeen reluctantly becomes the symbol of a mass rebellion against the autocratic Capitol.\",\n" +
                "         \"release_date\":\"2014-11-18\",\n" +
                "         \"genre_ids\":[\n" +
                "            878,\n" +
                "            12,\n" +
                "            53\n" +
                "         ],\n" +
                "         \"id\":131631,\n" +
                "         \"original_title\":\"The Hunger Games: Mockingjay - Part 1\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"The Hunger Games: Mockingjay - Part 1\",\n" +
                "         \"backdrop_path\":\"\\/83nHcz2KcnEpPXY50Ky2VldewJJ.jpg\",\n" +
                "         \"popularity\":17.097463,\n" +
                "         \"vote_count\":2808,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":6.78\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/jjBgi2r5cRt36xF6iNUEhzscEcb.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.\",\n" +
                "         \"release_date\":\"2015-06-09\",\n" +
                "         \"genre_ids\":[\n" +
                "            28,\n" +
                "            12,\n" +
                "            878,\n" +
                "            53\n" +
                "         ],\n" +
                "         \"id\":135397,\n" +
                "         \"original_title\":\"Jurassic World\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Jurassic World\",\n" +
                "         \"backdrop_path\":\"\\/dkMD5qlogeRMiEixC4YNPUvax2T.jpg\",\n" +
                "         \"popularity\":17.058163,\n" +
                "         \"vote_count\":4373,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":6.64\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/kqjL17yufvn9OVLyXYpvtyrFfak.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"An apocalyptic story set in the furthest reaches of our planet, in a stark desert landscape where humanity is broken, and most everyone is crazed fighting for the necessities of life. Within this world exist two rebels on the run who just might be able to restore order. There's Max, a man of action and a man of few words, who seeks peace of mind following the loss of his wife and child in the aftermath of the chaos. And Furiosa, a woman of action and a woman who believes her path to survival may be achieved if she can make it across the desert back to her childhood homeland.\",\n" +
                "         \"release_date\":\"2015-05-13\",\n" +
                "         \"genre_ids\":[\n" +
                "            878,\n" +
                "            53,\n" +
                "            28,\n" +
                "            12\n" +
                "         ],\n" +
                "         \"id\":76341,\n" +
                "         \"original_title\":\"Mad Max: Fury Road\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Mad Max: Fury Road\",\n" +
                "         \"backdrop_path\":\"\\/tbhdm8UJAb4ViCTsulYFL3lxMCd.jpg\",\n" +
                "         \"popularity\":15.066116,\n" +
                "         \"vote_count\":4481,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":7.35\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"Interstellar chronicles the adventures of a group of explorers who make use of a newly discovered wormhole to surpass the limitations on human space travel and conquer the vast distances involved in an interstellar voyage.\",\n" +
                "         \"release_date\":\"2014-11-05\",\n" +
                "         \"genre_ids\":[\n" +
                "            12,\n" +
                "            18,\n" +
                "            878\n" +
                "         ],\n" +
                "         \"id\":157336,\n" +
                "         \"original_title\":\"Interstellar\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Interstellar\",\n" +
                "         \"backdrop_path\":\"\\/xu9zaAevzQ5nnrsXN6JcahLnG4i.jpg\",\n" +
                "         \"popularity\":15.026879,\n" +
                "         \"vote_count\":4863,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":8.19\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/zSouWWrySXshPCT4t3UKCQGayyo.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"Since the dawn of civilization, he was worshipped as a god. Apocalypse, the first and most powerful mutant from Marvel’s X-Men universe, amassed the powers of many other mutants, becoming immortal and invincible. Upon awakening after thousands of years, he is disillusioned with the world as he finds it and recruits a team of powerful mutants, including a disheartened Magneto, to cleanse mankind and create a new world order, over which he will reign. As the fate of the Earth hangs in the balance, Raven with the help of Professor X must lead a team of young X-Men to stop their greatest nemesis and save mankind from complete destruction.\",\n" +
                "         \"release_date\":\"2016-05-18\",\n" +
                "         \"genre_ids\":[\n" +
                "            28,\n" +
                "            12,\n" +
                "            14,\n" +
                "            878\n" +
                "         ],\n" +
                "         \"id\":246655,\n" +
                "         \"original_title\":\"X-Men: Apocalypse\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"X-Men: Apocalypse\",\n" +
                "         \"backdrop_path\":\"\\/oQWWth5AOtbWG9o8SCAviGcADed.jpg\",\n" +
                "         \"popularity\":13.766738,\n" +
                "         \"vote_count\":70,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":4.71\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/7SGGUiTE6oc2fh9MjIk5M00dsQd.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"Armed with the astonishing ability to shrink in scale but increase in strength, con-man Scott Lang must embrace his inner-hero and help his mentor, Dr. Hank Pym, protect the secret behind his spectacular Ant-Man suit from a new generation of towering threats. Against seemingly insurmountable obstacles, Pym and Lang must plan and pull off a heist that will save the world.\",\n" +
                "         \"release_date\":\"2015-07-14\",\n" +
                "         \"genre_ids\":[\n" +
                "            878,\n" +
                "            28,\n" +
                "            12\n" +
                "         ],\n" +
                "         \"id\":102899,\n" +
                "         \"original_title\":\"Ant-Man\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Ant-Man\",\n" +
                "         \"backdrop_path\":\"\\/kvXLZqY0Ngl1XSw7EaMQO0C1CCj.jpg\",\n" +
                "         \"popularity\":13.578276,\n" +
                "         \"vote_count\":2970,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":6.89\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"The year is 2029. John Connor, leader of the resistance continues the war against the machines. At the Los Angeles offensive, John's fears of the unknown future begin to emerge when TECOM spies reveal a new plot by SkyNet that will attack him from both fronts; past and future, and will ultimately change warfare forever.\",\n" +
                "         \"release_date\":\"2015-06-23\",\n" +
                "         \"genre_ids\":[\n" +
                "            878,\n" +
                "            28,\n" +
                "            53,\n" +
                "            12\n" +
                "         ],\n" +
                "         \"id\":87101,\n" +
                "         \"original_title\":\"Terminator Genisys\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Terminator Genisys\",\n" +
                "         \"backdrop_path\":\"\\/bIlYH4l2AyYvEysmS2AOfjO7Dn8.jpg\",\n" +
                "         \"popularity\":13.145219,\n" +
                "         \"vote_count\":2023,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":6\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/hE24GYddaxB9MVZl1CaiI86M3kp.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"A cryptic message from Bond’s past sends him on a trail to uncover a sinister organization. While M battles political forces to keep the secret service alive, Bond peels back the layers of deceit to reveal the terrible truth behind SPECTRE.\",\n" +
                "         \"release_date\":\"2015-10-26\",\n" +
                "         \"genre_ids\":[\n" +
                "            28,\n" +
                "            12,\n" +
                "            80\n" +
                "         ],\n" +
                "         \"id\":206647,\n" +
                "         \"original_title\":\"Spectre\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Spectre\",\n" +
                "         \"backdrop_path\":\"\\/wVTYlkKPKrljJfugXN7UlLNjtuJ.jpg\",\n" +
                "         \"popularity\":12.555215,\n" +
                "         \"vote_count\":2597,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":6.19\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/sM33SANp9z6rXW8Itn7NnG1GOEs.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"In the animal city of Zootopia, a fast-talking fox who's trying to make it big goes on the run when he's framed for a crime he didn't commit. Zootopia's top cop, a self-righteous rabbit, is hot on his tail, but when both become targets of a conspiracy, they're forced to team up and discover even natural enemies can become best friends.\",\n" +
                "         \"release_date\":\"2016-02-12\",\n" +
                "         \"genre_ids\":[\n" +
                "            16,\n" +
                "            12,\n" +
                "            10751,\n" +
                "            35\n" +
                "         ],\n" +
                "         \"id\":269149,\n" +
                "         \"original_title\":\"Zootopia\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Zootopia\",\n" +
                "         \"backdrop_path\":\"\\/mhdeE1yShHTaDbJVdWyTlzFvNkr.jpg\",\n" +
                "         \"popularity\":11.982208,\n" +
                "         \"vote_count\":673,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":7.53\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/b77l5vmp6PYsc98LE6Uf1mXtmHh.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"As two evil sisters prepare to conquer the land, two renegades - Eric the Huntsman - who previously aided Snow White in defeating Ravenna, and his forbidden lover, Sara, set out to stop them.\",\n" +
                "         \"release_date\":\"2016-04-06\",\n" +
                "         \"genre_ids\":[\n" +
                "            28,\n" +
                "            12,\n" +
                "            18,\n" +
                "            14\n" +
                "         ],\n" +
                "         \"id\":290595,\n" +
                "         \"original_title\":\"The Huntsman Winter's War\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"The Huntsman Winter's War\",\n" +
                "         \"backdrop_path\":\"\\/nQ0UvXdxoMZguLuPj0sdV0U36KR.jpg\",\n" +
                "         \"popularity\":11.779945,\n" +
                "         \"vote_count\":203,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":5.4\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/o5Zt3nVlDJZLgeRgrhglgymDJym.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"In the near future, a virus of epic proportions has overtaken the planet. There are more infected than uninfected, and humanity is losing its grip on survival. Its only hope is finding a cure and keeping the infected contained. Lauren is a doctor who, after the fall of New York, arrives in Los Angeles to lead the hunt for uncontaminated civilian survivors. But nothing can prepare her crack team for the blood-soaked mayhem they are about to witness as they head into the Californian mean streets where everything is considered a trap… From director John (THE SCRIBBLER) Suits, a boundary-crashing, game-changing science fiction thriller featuring non-stop action from a first person shooter perspective, putting the audience in the middle of every fight whilst feeling in control of every punch thrown and shot fired. Welcome to the new model of immersive action thriller for the hardcore video game generation.\",\n" +
                "         \"release_date\":\"2016-05-03\",\n" +
                "         \"genre_ids\":[\n" +
                "            28,\n" +
                "            53,\n" +
                "            878\n" +
                "         ],\n" +
                "         \"id\":346651,\n" +
                "         \"original_title\":\"Pandemic\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"Pandemic\",\n" +
                "         \"backdrop_path\":\"\\/rhcbMOIRKsyU8xrOuocQBNdewXV.jpg\",\n" +
                "         \"popularity\":11.350795,\n" +
                "         \"vote_count\":15,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":3.47\n" +
                "      },\n" +
                "      {\n" +
                "         \"poster_path\":\"\\/w93GAiq860UjmgR6tU9h2T24vaV.jpg\",\n" +
                "         \"adult\":false,\n" +
                "         \"overview\":\"With the nation of Panem in a full scale war, Katniss confronts President Snow in the final showdown. Teamed with a group of her closest friends – including Gale, Finnick, and Peeta – Katniss goes off on a mission with the unit from District 13 as they risk their lives to stage an assassination attempt on President Snow who has become increasingly obsessed with destroying her. The mortal traps, enemies, and moral choices that await Katniss will challenge her more than any arena she faced in The Hunger Games.\",\n" +
                "         \"release_date\":\"2015-11-18\",\n" +
                "         \"genre_ids\":[\n" +
                "            28,\n" +
                "            12,\n" +
                "            18\n" +
                "         ],\n" +
                "         \"id\":131634,\n" +
                "         \"original_title\":\"The Hunger Games: Mockingjay - Part 2\",\n" +
                "         \"original_language\":\"en\",\n" +
                "         \"title\":\"The Hunger Games: Mockingjay - Part 2\",\n" +
                "         \"backdrop_path\":\"\\/qjn3fzCAHGfl0CzeUlFbjrsmu4c.jpg\",\n" +
                "         \"popularity\":11.281743,\n" +
                "         \"vote_count\":1634,\n" +
                "         \"video\":false,\n" +
                "         \"vote_average\":6.59\n" +
                "      }\n" +
                "   ],\n" +
                "   \"total_results\":19662,\n" +
                "   \"total_pages\":984\n" +
                "}";
        return temp;
    }
}
