films3000
=========
Films3000 est un programme de gestion automatique de fichiers de films, se basant sur le répertoire de Tmdb ainsi que sur une partie de Imdb. Une analyse des méta-infos des fichiers est aussi faites avec le programme mediainfo.

Le programme peut gèrer plusieurs fichiers pour un film et vérifier à quelle version du film un fichier spécifique appartient. Il affichera pour chaque fichier d'un film sélectionné, les méta-infos vidéos et audio, mais surtout la langue des pistes ainsi que les sous-titres disponibles. Une fonctionnalité d'auto-téléchargement de sous-titres à partir d'une source internet sera futurement disponible.

Le programme est testé dans les plus récents environnements Windows et Linux. 
Aucun test n'est directement fait sur les système Mac pour l'instant.

Dépendances
=========
Sur le système d'exécution
---------
* Mediainfo http://mediaarea.net/fr/MediaInfo

À la compilation
---------
* Api tmdb (et ses dépendances) https://github.com/Omertron/api-themoviedb
* H2 http://www.h2database.com
* Jackson http://jackson.codehaus.org/
* Jna https://github.com/twall/jna
* Jsoup http://jsoup.org/
