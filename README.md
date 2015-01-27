# RAJ_project_03 (Rest and JMS)

-4 aplikacje:

1. Web service z list¹ leków i zni¿ek
	- kilka leków
	- jeden lek mo¿e mieæ kilka zni¿ek
	- ka¿da zni¿ka zawiera komentarz (komu nale¿y siê taka zni¿ka)
	- mo¿liwoœæ dodania nowego leku wraz ze zni¿kami
	- lek mo¿e nie mieæ ¿adnej zni¿ki (wtedy jest na 100%)
	- mo¿liwoœæ zmiany listy zni¿ek dla danego leku
	- mo¿liwoœæ usuniêcia leku
	- mo¿liwoœæ pobrania zni¿ek dla danego leku oraz listy wszystkich leków wraz ze zni¿kami
	- wszêdzie format json, opcjonalnie xml (ale niekoniecznie)

2. NFZ
- komunikuje siê z web servicem za poœrednictwem zapytañ
- na pocz¹tku wyœwietla listê wszystkich leków, które s¹ w web servisie + zni¿ki
- mo¿liwoœæ dodania nowego leku ze zni¿kami (dodaje do web servisu)
- mo¿liwoœæ usuniêcia leku
- mo¿liwoœæ zmodyfikowania zni¿ek do leku
- mo¿liwoœæ zmodyfikowania komentarza (tego, komu nale¿y siê jaka zni¿ka) - czyli w sumie zmodyfikowania zni¿ki
3. Lekarz
- tworzy receptê dla danego pacjenta (recepta zawiera dane pacjenta - Imiê, Nazwisko, PESEL, adres zamieszkania - oraz dane dotycz¹ce leku (nazwa + przyznana zni¿ka)
- wysy³a zapytanie do web servisu, ¿eby wyœwietliæ zni¿ki dla danego leku
- stworzon¹ receptê wysy³a poprzez JMS do (A: bazy recept B: pierwszej lepszej apteki, apteka sprawdza, czy ma dany lek, jeœli nie, to wysy³a receptê do kolejnej i tak w kó³ko do skutku)
4. Apteka
- podejœcie A: 
	- wysy³a zapytanie JMS do bazy recept o wypisane recepty dla pacjenta o danym numerze PESEL;
	- wysy³a do bazy recept informacjê o tym, który lek zosta³ sprzedany (mo¿e sprzedaæ tylko lek, na który zosta³a wypisana jakaœ recepta; zak³adamy, ¿e apteka ma wszystkie leki w nieograniczonej iloœci)
- podejœcie B:
	- odbiera receptê wys³an¹ do lekarza
	- sprawdza, czy ma na stanie dany lek
	- jeœli tak, sprzedaje lek (zmniejsza siê iloœæ danego leku) i odsy³a informacjê do lekarza (JMS)
	- jeœli nie, wysy³a informacjê do lekarza
5. Baza recept (tylko w podejœciu A)
- odbiera zapytanie JMS od Apteki
- wysy³a wiadomoœæ JMS do Apteki z list¹ recept dla danego pacjenta
- odbiera wiadomoœæ JMS od Apteki o zrealizowanej recepcie - skreœla j¹ z listy recept
