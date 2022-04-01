# demoProject
Demo Project

A Project egy könyvtári katalógus megvalósítása Java nyelven, adatbázisok segítségével. A katalógusban lehetnek sima könyvek és audio példányok is.
Az alkalmazás kétrétegű: egy repository és egy service réteget tartalmaz.

A repository réteg felel az adatbázison elvégzendő műveletekért, megvalósítva a tartós tárolás négy klasszikus alapműveletét: Create, Read, Update, Delete. Ennek megvalósítására a Spring Framework JdbcTemplate osztályát használtam.

A service réteg valósítja meg az üzleti logikát, jelen esetben egy konzolos kezelőfelülethez való kapcsolódást. A service rétegnek repository attribútumai vannak, amiket konstruktoron keresztül kap meg, és csak ezen rétegen keresztül lehet kapcsolódni a repository-hoz, és ezáltal az adatbázishoz. 

A program konzolos megvalósítást kapott, a különböző menüpontok képezik le az adatbázis műveleteket.
