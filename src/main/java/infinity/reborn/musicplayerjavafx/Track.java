package infinity.reborn.musicplayerjavafx;

public class Track {
    private String id;
    private String title;
    private Artist artist;
    private Album album;
    private String preview;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public Album getAlbum() {
        return album;
    }

    public String getPreview() {
        return preview;
    }

    public static class Artist {
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class Album {
        private String id;
        private String title;
        private String cover;

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getCover() {
            return cover;
        }
    }
}
