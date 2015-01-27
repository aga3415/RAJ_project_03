# RAJ_project_03 (Rest and JMS)

-4 aplikacje:

1. Web service z list� lek�w i zni�ek
	- kilka lek�w
	- jeden lek mo�e mie� kilka zni�ek
	- ka�da zni�ka zawiera komentarz (komu nale�y si� taka zni�ka)
	- mo�liwo�� dodania nowego leku wraz ze zni�kami
	- lek mo�e nie mie� �adnej zni�ki (wtedy jest na 100%)
	- mo�liwo�� zmiany listy zni�ek dla danego leku
	- mo�liwo�� usuni�cia leku
	- mo�liwo�� pobrania zni�ek dla danego leku oraz listy wszystkich lek�w wraz ze zni�kami
	- wsz�dzie format json, opcjonalnie xml (ale niekoniecznie)

2. NFZ
- komunikuje si� z web servicem za po�rednictwem zapyta�
- na pocz�tku wy�wietla list� wszystkich lek�w, kt�re s� w web servisie + zni�ki
- mo�liwo�� dodania nowego leku ze zni�kami (dodaje do web servisu)
- mo�liwo�� usuni�cia leku
- mo�liwo�� zmodyfikowania zni�ek do leku
- mo�liwo�� zmodyfikowania komentarza (tego, komu nale�y si� jaka zni�ka) - czyli w sumie zmodyfikowania zni�ki
3. Lekarz
- tworzy recept� dla danego pacjenta (recepta zawiera dane pacjenta - Imi�, Nazwisko, PESEL, adres zamieszkania - oraz dane dotycz�ce leku (nazwa + przyznana zni�ka)
- wysy�a zapytanie do web servisu, �eby wy�wietli� zni�ki dla danego leku
- stworzon� recept� wysy�a poprzez JMS do (A: bazy recept B: pierwszej lepszej apteki, apteka sprawdza, czy ma dany lek, je�li nie, to wysy�a recept� do kolejnej i tak w k�ko do skutku)
4. Apteka
- podej�cie A: 
	- wysy�a zapytanie JMS do bazy recept o wypisane recepty dla pacjenta o danym numerze PESEL;
	- wysy�a do bazy recept informacj� o tym, kt�ry lek zosta� sprzedany (mo�e sprzeda� tylko lek, na kt�ry zosta�a wypisana jaka� recepta; zak�adamy, �e apteka ma wszystkie leki w nieograniczonej ilo�ci)
- podej�cie B:
	- odbiera recept� wys�an� do lekarza
	- sprawdza, czy ma na stanie dany lek
	- je�li tak, sprzedaje lek (zmniejsza si� ilo�� danego leku) i odsy�a informacj� do lekarza (JMS)
	- je�li nie, wysy�a informacj� do lekarza
5. Baza recept (tylko w podej�ciu A)
- odbiera zapytanie JMS od Apteki
- wysy�a wiadomo�� JMS do Apteki z list� recept dla danego pacjenta
- odbiera wiadomo�� JMS od Apteki o zrealizowanej recepcie - skre�la j� z listy recept
