 <!-- Separar en otro archivo html para reusarlo -->
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
            <div class="sidebar-sticky pt-3">
                <ul class="nav flex-column">
                    <li class="nav-item" style="margin: 2px">
                    <!-- 
                        <button ng-click="openCliente()" onclick="location.href='http://www.example.com'"  type="button" class="btn btn-outline-info"><span
                                class="fas fa-address-book"></span>
                            Cliente <span class="sr-only">(current)</span></button>
                             -->
                    <button  onclick="location.href='AltaCliente'"  type="button" class="btns" style="width: 100px; height: 30px;"><span
                         class="fas fa-address-book"></span>
                         Cliente <span class="sr-only">(current)</span></button>
                            
                    </li>
                    <li class="nav-item" style="margin: 2px">
                        <button onclick="location.href='AltaRole'" type="button" class="btns" style="width: 100px; height: 30px;"><span
                                class="fas fa-suitcase-rolling"></span>
                            Role <span class="sr-only">(current)</span></button>
                    </li>
                    <li class="nav-item" style="margin: 2px">
                        <button onclick="location.href='ClienteRole'" type="button" class="btns" style="width: 175px; height: 30px;"><span
                                class="fas fa-user-lock"></span> Autorizacion<span class="sr-only">(current)</span></button>
                    </li>
                    <li class="nav-item" style="margin: 2px">
                        <button onclick="location.href='AltaComunidad'"  type="button" class="btns" style="width: 175px; height: 30px;"><span
                                class="fas fa-globe-americas"></span>
                            Comunidad <span class="sr-only">(current)</span></button>
                    </li>                
                    <li class="nav-item" style="margin: 2px">
                        <button onclick="location.href='DetalleServicio'" type="button" class="btns" style="width: 205px; height: 30px;"><span
                                class="fas fa-info-circle"></span>
                            Detalle del Servicio <span class="sr-only">(current)</span></button>
                    </li>
                </ul>

                <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                    <span>Otros Accesos</span>
                    <a class="d-flex align-items-center text-muted" href="#" aria-label="Add a new report">
                        <span data-feather="plus-circle"></span>
                    </a>
                </h6>
                <ul class="nav flex-column mb-2">
                    <li class="nav-item">
                        <button type="button" class="btns" style="width: 100px; height: 30px;"><span
                                class="fas fa-map-marked-alt"></span>
                            Mapas<span class="sr-only">(current)</span></button>
                    </li>
                </ul>
            </div>
        </nav>