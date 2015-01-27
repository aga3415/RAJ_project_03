# RAJ_project_03 (Rest and JMS)

-4 aplikacje:

1. Web service z list¹ leków i zni¿ek

a) kilka leków
b. jeden lek mo¿e mieæ kilka zni¿ek
-c) ka¿da zni¿ka zawiera komentarz (komu nale¿y siê taka zni¿ka)
-d) mo¿liwoœæ dodania nowego leku wraz ze zni¿kami
-e) lek mo¿e nie mieæ ¿adnej zni¿ki (wtedy jest na 100%)
-f) mo¿liwoœæ zmiany listy zni¿ek dla danego leku
-g) mo¿liwoœæ usuniêcia leku
-h) mo¿liwoœæ pobrania zni¿ek dla danego leku oraz listy wszystkich leków wraz ze zni¿kami
-i) wszêdzie format json, opcjonalnie xml (ale niekoniecznie)

2) NFZ
a) komunikuje siê z web servicem za poœrednictwem zapytañ
b) na pocz¹tku wyœwietla listê wszystkich leków, które s¹ w web servisie + zni¿ki
c) mo¿liwoœæ dodania nowego leku ze zni¿kami (dodaje do web servisu)
d) mo¿liwoœæ usuniêcia leku
e) mo¿liwoœæ zmodyfikowania zni¿ek do leku
f) mo¿liwoœæ zmodyfikowania komentarza (tego, komu nale¿y siê jaka zni¿ka) - czyli w sumie zmodyfikowania zni¿ki

3) Lekarz
a) tworzy receptê dla danego pacjenta (recepta zawiera dane pacjenta - Imiê, Nazwisko, PESEL, adres zamieszkania - oraz dane dotycz¹ce leku (nazwa + przyznana zni¿ka)
b) wysy³a zapytanie do web servisu, ¿eby wyœwietliæ zni¿ki dla danego leku
c) stworzon¹ receptê wysy³a poprzez JMS do (A: bazy recept B: pierwszej lepszej apteki, apteka sprawdza, czy ma dany lek, jeœli nie, to wysy³a receptê do kolejnej i tak w kó³ko do skutku)

4) Apteka
a) podejœcie A: 
	- wysy³a zapytanie JMS do bazy recept o wypisane recepty dla pacjenta o danym numerze PESEL;
	- wysy³a do bazy recept informacjê o tym, który lek zosta³ sprzedany (mo¿e sprzedaæ tylko lek, na który zosta³a wypisana jakaœ recepta; zak³adamy, ¿e apteka ma wszystkie leki w nieograniczonej iloœci)
b) podejœcie B:
	- odbiera receptê wys³an¹ do lekarza
	- sprawdza, czy ma na stanie dany lek
	- jeœli tak, sprzedaje lek (zmniejsza siê iloœæ danego leku) i odsy³a informacjê do lekarza (JMS)
	- jeœli nie, wysy³a informacjê do lekarza

5) Baza recept (tylko w podejœciu A)
a) odbiera zapytanie JMS od Apteki
b) wysy³a wiadomoœæ JMS do Apteki z list¹ recept dla danego pacjenta
c) odbiera wiadomoœæ JMS od Apteki o zrealizowanej recepcie - skreœla j¹ z listy recept
